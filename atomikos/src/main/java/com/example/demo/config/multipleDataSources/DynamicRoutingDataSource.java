package com.example.demo.config.multipleDataSources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * author :xie
 * Email: 1487471733@qq.com
 * Date: 2020/8/1 22:58
 * Describe:
 * @author xiehuan
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

  private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

  private static String defaultTargetDataSourceName;

  private static int  i = 0;

  @Override
  protected Object determineCurrentLookupKey() {
    String dataSourceName = getDataSource();
    System.out.println(dataSourceName);
    return getDataSource();
  }

  public DynamicRoutingDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources, String defaultDataSourceName) {
    super.setDefaultTargetDataSource(defaultTargetDataSource);
    super.setTargetDataSources(targetDataSources);
    //super.afterPropertiesSet();
    defaultTargetDataSourceName = defaultDataSourceName;
  }

  public static void setDataSource(String dataSource) {
    contextHolder.set(dataSource);
  }

  public static String getDataSource() {

    return contextHolder.get();
/*    if (StringUtils.isEmpty(contextHolder.get())) {
      return defaultTargetDataSourceName;
    }
    else {
      return contextHolder.get();
    }*/
  }

  public static void clearDataSource() {
    contextHolder.remove();
  }
}
