package com.revengemission.demo.dynamicdatasource.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by revenge mission on 18-6-17.
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "jdbc1Datasource")
    @ConfigurationProperties(prefix = "jdbc1.datasource")
    public DataSource jdbc1Datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbc1DatasourceInitializer")
    public DataSourceInitializer jdbc1DatasourceInitializer(@Qualifier("jdbc1Datasource") DataSource datasource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/sql/mysql-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/sql/mysql-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean(name = "jdbc2Datasource")
    @ConfigurationProperties(prefix = "jdbc2.datasource")
    public DataSource jdbc2Datasource() {
        return DataSourceBuilder.create().build();
    }
/*
    @Bean(name = "jdbc2DatasourceInitializer")
    public DataSourceInitializer jdbc2DatasourceInitializer(@Qualifier("jdbc2Datasource") DataSource datasource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/sql/mssql-schema.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
*/

    @Bean(name = "jdbc3Datasource")
    @ConfigurationProperties(prefix = "jdbc3.datasource")
    public DataSource jdbc3Datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbc3DatasourceInitializer")
    public DataSourceInitializer jdbc3DatasourceInitializer(@Qualifier("jdbc3Datasource") DataSource datasource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/sql/h2-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/sql/h2-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean(name = "jdbc4Datasource")
    @ConfigurationProperties(prefix = "jdbc4.datasource")
    public DataSource jdbc4Datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbc4DatasourceInitializer")
    public DataSourceInitializer jdbc4DatasourceInitializer(@Qualifier("jdbc4Datasource") DataSource datasource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/sql/h2-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/sql/h2-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
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
        dynamicDataSource.setDefaultTargetDataSource(jdbc1Datasource());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(16);
        dsMap.put("jdbc1Datasource", jdbc1Datasource());
        dsMap.put("jdbc2Datasource", jdbc2Datasource());
        dsMap.put("jdbc3Datasource", jdbc3Datasource());
        dsMap.put("jdbc4Datasource", jdbc4Datasource());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mappers/*/*.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sqlSessionFactoryBean;
    }
}
