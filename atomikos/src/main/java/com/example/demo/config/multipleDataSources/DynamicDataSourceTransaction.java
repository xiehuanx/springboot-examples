package com.example.demo.config.multipleDataSources;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2020/8/3 20:25
 * Describe:
 */
public class DynamicDataSourceTransaction implements Transaction {
  private static final Log LOGGER = LogFactory.getLog(DynamicDataSourceTransaction.class);

  private final DataSource dataSource;

  private Connection mainConnection;

  private String mainDatabaseIdentification;

  private ConcurrentMap<String, Connection> otherConnectionMap;


  private boolean isConnectionTransactional;

  private boolean autoCommit;


  public DynamicDataSourceTransaction(DataSource dataSource) {
    Assert.notNull(dataSource, "No DataSource specified");
    this.dataSource = dataSource;
    otherConnectionMap = new ConcurrentHashMap<>();
    mainDatabaseIdentification = DynamicRoutingDataSource.getDataSource();
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Connection getConnection() throws SQLException {
    String databaseIdentification = DynamicRoutingDataSource.getDataSource();
    if (databaseIdentification.equals(mainDatabaseIdentification)) {
      if (mainConnection != null) {
        return mainConnection;
      }
      else {
        openMainConnection();
        mainDatabaseIdentification = databaseIdentification;
        return mainConnection;
      }
    } else {
      if (!otherConnectionMap.containsKey(databaseIdentification)) {
        try {
          Connection conn = dataSource.getConnection();
          otherConnectionMap.put(databaseIdentification, conn);
        } catch (SQLException ex) {
          throw new CannotGetJdbcConnectionException("Could not get JDBC Connection", ex);
        }
      }
      return otherConnectionMap.get(databaseIdentification);
    }

  }


  private void openMainConnection() throws SQLException {
    this.mainConnection = DataSourceUtils.getConnection(this.dataSource);
    this.autoCommit = this.mainConnection.getAutoCommit();
    this.isConnectionTransactional = DataSourceUtils.isConnectionTransactional(this.mainConnection, this.dataSource);

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(
              "JDBC Connection ["
                      + this.mainConnection
                      + "] will"
                      + (this.isConnectionTransactional ? " " : " not ")
                      + "be managed by Spring");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void commit() throws SQLException {
    if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Committing JDBC Connection [" + this.mainConnection + "]");
      }
      this.mainConnection.commit();
      for (Connection connection : otherConnectionMap.values()) {
        connection.commit();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void rollback() throws SQLException {
    if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Rolling back JDBC Connection [" + this.mainConnection + "]");
      }
      this.mainConnection.rollback();
      for (Connection connection : otherConnectionMap.values()) {
        connection.rollback();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void close() throws SQLException {
    DataSourceUtils.releaseConnection(this.mainConnection, this.dataSource);
    for (Connection connection : otherConnectionMap.values()) {
      DataSourceUtils.releaseConnection(connection, this.dataSource);
    }
  }

  @Override
  public Integer getTimeout() throws SQLException {
    return null;
  }
}
