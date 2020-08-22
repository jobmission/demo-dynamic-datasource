package com.revengemission.demo.dynamicdatasource.service.impl;

import com.revengemission.demo.dynamicdatasource.aop.SwitchDataSource;
import com.revengemission.demo.dynamicdatasource.persistence.h2.mapper.MonthStatMapper;
import com.revengemission.demo.dynamicdatasource.service.MonthStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthStatServiceImpl implements MonthStatService {

    @Autowired
    MonthStatMapper monthStatMapper;

    @SwitchDataSource
    @Override
    public long count(String datasource) {
        return monthStatMapper.count();
    }

    @SwitchDataSource
    @Override
    public long insert(String datasource, String valueDate, String value) {
        return monthStatMapper.insert(valueDate, value);
    }
}
