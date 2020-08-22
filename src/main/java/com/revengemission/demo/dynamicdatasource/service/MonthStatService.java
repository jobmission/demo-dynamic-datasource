package com.revengemission.demo.dynamicdatasource.service;

public interface MonthStatService {
    long count(String datasource);

    long insert(String datasource,String valueDate, String value);
}
