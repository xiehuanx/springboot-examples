package com.example.demo.config.multipleDataSources;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import javax.sql.DataSource;

/**
 * author :xie
 * Email: 1487471733@qq.com
 * Date: 2020/8/3 11:04
 * Describe:
 */
public class DynamicDataSourceTransactionFactory extends SpringManagedTransactionFactory {

  @Override
  public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
    return new DynamicDataSourceTransaction(dataSource);
  }
}
