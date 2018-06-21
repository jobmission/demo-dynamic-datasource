package com.revengemission.demo.dynamicdatasource.service.impl;

import com.revengemission.demo.dynamicdatasource.aop.SwitchDataSource;
import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity.TagIndex;
import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.mapper.TagIndexMapper;
import com.revengemission.demo.dynamicdatasource.service.TagIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by revenge mission on 18-6-17.
 */
@Service
public class TagIndexServiceImpl implements TagIndexService {

    @Autowired
    TagIndexMapper tagIndexMapper;

    @SwitchDataSource
    @Override
    public List<TagIndex> list(String datasource, String tableName) {
        return tagIndexMapper.select(tableName);
    }

    @SwitchDataSource
    @Override
    public int count(String datasource, String tableName) {
        return tagIndexMapper.count(tableName);
    }
}
