package com.revengemission.demo.dynamicdatasource.service;

import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity.ValueTableFloat;

import java.util.List;

/**
 * Created by revenge mission on 18-6-17.
 */
public interface ValueTableFloatService {
    //    根据参数ID,查寻当天的数据
    List<ValueTableFloat> list(String datasource, String tableName, int id);

    long count(String datasource, String tableName);

    long countById(String datasource, String tableName, int id);
}
