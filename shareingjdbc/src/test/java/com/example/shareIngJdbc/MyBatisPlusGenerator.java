package com.example.shareIngJdbc;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/9/1
 * Time: 18:18
 * Describe:
 */
public class MyBatisPlusGenerator {

    //代码输出目录
    private static final String MAIN_PATH = System.getProperty("user.dir") + File.separator + "target" + File.separator + "code";
    //作者
    private static final String AUTHOR = "xie";
    //表名,多个用","分割
    private static final String TABLES = "classes,user_classes,student_basic_info,student_company_info";
    //数据库链接
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Xiehuan!3";
    private static final String URL = "jdbc:mysql://cdb-morretuw.cd.tencentcdb.com:10016/xiaoyaninfo";
    //java代码输出目录
    private static final String JAVA_PATH = MAIN_PATH + "/java";
    //基本包路径
    private static final String BASE_PACKAGE = "xyz.xiaoyaninfo.xiaoyaninfo.common";
    //模块名称
    private static final String MODULE_NAME = "xyz.xiaoyaninfo.xiaoyaninfo.common.persistence";
    private static final String TYPE_NAME = "report";
    //    private static final String MODULE_NAME = "common.tableQuery";
//    private static final String TYPE_NAME = "common";
    private static final String SUB_PACKAGE = BASE_PACKAGE ;
    private static final String COMMON_PACKAGE = BASE_PACKAGE+"."+"report";


    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(JAVA_PATH);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        // .setKotlin(true) 是否生成 kotlin 代码
        gc.setAuthor(AUTHOR);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%Mapper");
        gc.setXmlName("%Mapper");
        gc.setServiceName("%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(false);
        mpg.setGlobalConfig(gc);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);

        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        dsc.setUrl(URL);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix(new String[] {  });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(TABLES.split(",")); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
/*        strategy.setSuperEntityClass(COMMON_PACKAGE+".base.entity.BaseEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        strategy.setSuperMapperClass(COMMON_PACKAGE+".base.dao.BaseMapper");
        // 自定义 service 父类
        strategy.setSuperServiceClass(COMMON_PACKAGE+".base.service.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass(COMMON_PACKAGE+".base.service.impl.BaseServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass(COMMON_PACKAGE+".base.controller.BaseController");*/
        // 生成 RestController 风格
        strategy.setRestControllerStyle(true);


        mpg.setStrategy(strategy);

        // 包配置
        // 注意不同的模块生成时要修改对应模块包名
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity(BASE_PACKAGE+"."+MODULE_NAME+".entity");
        pc.setMapper(BASE_PACKAGE+"."+MODULE_NAME+".dao");
        pc.setXml(BASE_PACKAGE+"."+MODULE_NAME+".dao.mapping");
        pc.setService(BASE_PACKAGE+"."+MODULE_NAME+".service");
        pc.setServiceImpl(BASE_PACKAGE+"."+MODULE_NAME+".service.impl");
        pc.setController(BASE_PACKAGE+"."+MODULE_NAME+".controller");

        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String,Object>();
                map.put("typeName", TYPE_NAME);
                map.put("basePackage", BASE_PACKAGE);
                map.put("subPackage", SUB_PACKAGE);
                map.put("moduleName", MODULE_NAME);
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
/*        TemplateConfig tc = new TemplateConfig();
        tc.setController("/mybatis-templates/controller.java.vm");
        tc.setEntity("/mybatis-templates/entity.java.vm");
        tc.setMapper("/mybatis-templates/mapper.java.vm");
        tc.setXml("/mybatis-templates/mapper.xml.vm");
        tc.setService("/mybatis-templates/service.java.vm");
        tc.setServiceImpl("/mybatis-templates/serviceImpl.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);*/

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println("success");

    }
}
