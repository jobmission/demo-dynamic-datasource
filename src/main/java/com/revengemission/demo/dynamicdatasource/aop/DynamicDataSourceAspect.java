package com.revengemission.demo.dynamicdatasource.aop;

import com.revengemission.demo.dynamicdatasource.config.DataSourceContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created by revenge mission on 18-6-17.
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.revengemission.demo.dynamicdatasource.aop.SwitchDataSource)")
    public void dataSource() {
    }

    @Before("dataSource()")
    public void beforeSwitchDS(JoinPoint point) {

        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            Object[] args = point.getArgs();
            if (args != null && args.length > 0) {
                dataSource = String.valueOf(args[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(dataSource)) {
            System.out.println("dataSource to be null!");
        }

        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);

    }


    @After("dataSource()")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }
}
