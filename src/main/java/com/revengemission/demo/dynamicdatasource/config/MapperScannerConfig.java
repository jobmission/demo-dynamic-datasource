package com.revengemission.demo.dynamicdatasource.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.revengemission.demo.dynamicdatasource.persistence.app.mapper", "com.revengemission.demo.dynamicdatasource.persistence.sqlserver.mapper"})
public class MapperScannerConfig {
}

