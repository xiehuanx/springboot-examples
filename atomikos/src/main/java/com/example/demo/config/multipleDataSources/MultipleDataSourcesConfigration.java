package com.example.demo.config.multipleDataSources;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * author :xie
 * Email: 1487471733@qq.com
 * Date: 2020/8/1 19:59
 * Describe:
 * @author xiehuan
 */
@Configuration
@Slf4j
public class MultipleDataSourcesConfigration implements EnvironmentAware, ImportBeanDefinitionRegistrar {

  private final Map<String, AtomikosDataSourceBean> dataSourceMap = new LinkedHashMap<>();

  /**
   * 参数绑定工具 springboot2.0新推出
   */
  private Binder binder;

  /**
   * 配置上下文（也可以理解为配置文件的获取工具）
   */
  private Environment evn;
  /**
   * mapper interface 的父目录
   */
  @Value("${xiehuan.mybatis-plus.mapper.package}")
  private String pageages;

  /**
   * 别名
   */
  private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();

  /**
   * 由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
   */
  static {
    aliases.addAliases("url", new String[]{"jdbc-url"});
    aliases.addAliases("username", new String[]{"user"});
  }
  @Override
  public void setEnvironment(Environment environment) {
    this.evn = environment;
    // 绑定配置器
    binder = Binder.get(evn);
    String prefix = "xiehuan.data-source.";
    String names = environment.getProperty(prefix + "names");
    assert names != null;
    for(String each :names.split(",")) {
      Properties map = Binder.get(environment).bind("xiehuan.data-source." + each, Properties.class).orElse(null);
      AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
      ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
      ds.setUniqueResourceName(each+"DataSource");
      ds.setPoolSize(5);
      ds.setXaProperties(map);
      ds.setTestQuery("SELECT 1");
      dataSourceMap.put(each, ds);
    }
  }

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    dataSourceMap.forEach((k,v) -> {
      BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
      builder.addPropertyValue("basePackage", "com.example.demo.mapper."+k);
      registry.registerBeanDefinition(k+"mapperScannerConfigurer", builder.getBeanDefinition());
      BeanDefinitionBuilder dataSourceSimpleBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSource.class, () -> {
        return v;
      });
      if (k.contains("xiex")) {
        dataSourceSimpleBuilder.setPrimary(true);
      }
      registry.registerBeanDefinition(k + "DataSource", dataSourceSimpleBuilder.getBeanDefinition());
    });
  }

  // 配置数据源
  @Bean(name = "dynamicRoutingDataSource")
  public DynamicRoutingDataSource dataSource() {
    //注册动态数据源
    Map<Object, Object> targetDataSources = new LinkedHashMap<>();
    dataSourceMap.forEach((k, v) -> {
      v.setUniqueResourceName(k+"_");
      targetDataSources.put(k, v);
    });
    return new DynamicRoutingDataSource(dataSourceMap.get("xiex"),targetDataSources, "xiex" );
  }



  @Primary
  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicRoutingDataSource") DataSource dataSource)
          throws Exception {
    MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setTransactionFactory(new DynamicDataSourceTransactionFactory());
    return bean.getObject();
  }
  @Bean(name="sqlSessionTemplate")
  @Primary
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  /**
   * 分布式事务使用JTA管理，不管有多少个数据源只要配置一个 JtaTransactionManager
   * atomikos事务管理器
   * @return
   */
  public UserTransactionManager userTransactionManager() {
    UserTransactionManager userTransactionManager = new UserTransactionManager();
    userTransactionManager.setForceShutdown(true);
    return userTransactionManager;
  }

  public UserTransactionImp userTransactionImp() throws SystemException {
    UserTransactionImp userTransactionImp = new UserTransactionImp();
    userTransactionImp.setTransactionTimeout(5000);
    return userTransactionImp;
  }

  @Bean
  public JtaTransactionManager jtaTransactionManager() throws SystemException {
    JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
    jtaTransactionManager.setTransactionManager(userTransactionManager());
    jtaTransactionManager.setUserTransaction(userTransactionImp());
    jtaTransactionManager.setAllowCustomIsolationLevels(true);
    return jtaTransactionManager;
  }

  @Bean
  public StatFilter statFilter(){
    StatFilter statFilter = new StatFilter();
    //slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
    statFilter.setLogSlowSql(true);
    //SQL合并配置
    statFilter.setMergeSql(true);
    //slowSqlMillis的缺省值为3000，也就是3秒。
    statFilter.setSlowSqlMillis(1000);
    return statFilter;
  }

  @Bean
  public WallFilter wallFilter(){
    WallFilter wallFilter = new WallFilter();
    //允许执行多条SQL
    WallConfig config = new WallConfig();
    config.setMultiStatementAllow(true);
    wallFilter.setConfig(config);
    return wallFilter;
  }





}