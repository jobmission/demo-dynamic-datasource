package com.revengemission.demo.dynamicdatasource.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by revenge mission on 18-6-17.
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "app.datasource") // application.properteis中对应属性的前缀
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "sqlserver1DataSource")
    @ConfigurationProperties(prefix = "sqlserver1.datasource") // application.properteis中对应属性的前缀
    public DataSource sqlserver1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlserver2DataSource")
    @ConfigurationProperties(prefix = "sqlserver2.datasource") // application.properteis中对应属性的前缀
    public DataSource sqlserver2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlserver3DataSource")
    @ConfigurationProperties(prefix = "sqlserver3.datasource") // application.properteis中对应属性的前缀
    public DataSource sqlserver3DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlserver4DataSource")
    @ConfigurationProperties(prefix = "sqlserver4.datasource") // application.properteis中对应属性的前缀
    public DataSource sqlserver4DataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(appDataSource());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("appDataSource", appDataSource());
        dsMap.put("sqlserver1DataSource", sqlserver1DataSource());
        dsMap.put("sqlserver2DataSource", sqlserver2DataSource());
        dsMap.put("sqlserver3DataSource", sqlserver3DataSource());
        dsMap.put("sqlserver4DataSource", sqlserver4DataSource());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "false");
        properties.setProperty("autoRuntimeDialect", "true");
        pageInterceptor.setProperties(properties);
        Interceptor[] plugins = new Interceptor[]{pageInterceptor};
        sqlSessionFactoryBean.setPlugins(plugins);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(ArrayUtils.addAll(resolver.getResources("classpath*:sqlserver/*.xml"), resolver.getResources("classpath*:appMappers/*.xml")));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sqlSessionFactoryBean;
    }
}
