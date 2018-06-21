package com.revengemission.demo.dynamicdatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class DataVisualizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataVisualizationApplication.class, args);
    }
}
