package com.revengemission.demo.dynamicdatasource.service;

import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity.TagIndex;

import java.util.List;

/**
 * Created by revenge mission on 18-6-17.
 */
public interface TagIndexService {
    List<TagIndex> list(String datasource, String tableName);

    int count(String datasource, String tableName);
}
