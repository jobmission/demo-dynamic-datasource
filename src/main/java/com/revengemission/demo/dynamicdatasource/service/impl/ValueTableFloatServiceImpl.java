package com.revengemission.demo.dynamicdatasource.service.impl;

import com.revengemission.demo.dynamicdatasource.aop.SwitchDataSource;
import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity.ValueTableFloat;
import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.mapper.ValueTableFloatMapper;
import com.revengemission.demo.dynamicdatasource.service.ValueTableFloatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by revenge mission on 18-6-17.
 */
@Service
public class ValueTableFloatServiceImpl implements ValueTableFloatService {
    @Autowired
    ValueTableFloatMapper valueTableFloatMapper;

    @SwitchDataSource
    @Override
    public List<ValueTableFloat> list(String datasource, String tableName, int id) {
        return valueTableFloatMapper.select(tableName, id);
    }

    @SwitchDataSource
    @Override
    public long count(String datasource, String tableName) {
        return valueTableFloatMapper.count(tableName);
    }

    @SwitchDataSource
    @Override
    public long countById(String datasource, String tableName, int id) {
        return valueTableFloatMapper.countById(tableName, id);
    }
}
