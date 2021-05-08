package com.example.demo.config.multipleDataSources.aspect;

import com.example.demo.annotation.DataSource;
import com.example.demo.config.multipleDataSources.DynamicRoutingDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017/9/16 22:20
 */
//@Aspect
//@Configuration
//@Order(0)
public class DataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Pointcut("execution(* com.example.demo.service.impl.*.*(..)) && @target(com.example.demo.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource ds = null;
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ds = resolveDataSource(target, method);

        if (ds == null) {
            DynamicRoutingDataSource.setDataSource("xiex");
        } else {
            DynamicRoutingDataSource.setDataSource(ds.name());
            logger.info("set datasource is " + ds.name());
        }
        return point.proceed();
    }

    @After("dataSourcePointCut()")
    public void restoreDataSource(JoinPoint point) {
        DynamicRoutingDataSource.clearDataSource();
    }

    /*
     * 获取最终的dataSource
     *
     * @param clazz
     * @param method
     */
    private DataSource resolveDataSource(Class<?> clazz, Method method) {
        try {
            DataSource ds = null;
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                ds = clazz.getAnnotation(DataSource.class);
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                ds = m.getAnnotation(DataSource.class);
//                DynamicDataSource.setDataSource(ds.name());
//                logger.info("set datasource is " + ds.name());
//                logger.info(DynamicDataSource.getDataSource());
            }
            return ds;
        } catch (Exception e) {
            System.out.println(clazz + ":" + e.getMessage());
        }
        return null;
    }
}
