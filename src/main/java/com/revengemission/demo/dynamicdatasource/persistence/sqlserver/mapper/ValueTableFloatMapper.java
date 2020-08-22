package com.revengemission.demo.dynamicdatasource.persistence.sqlserver.mapper;

import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity.ValueTableFloat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ValueTableFloatMapper {
    long count(@Param("tableName") String tableName);

    //    根据参数ID,查寻当天的数据
    List<ValueTableFloat> select(@Param("tableName") String tableName, @Param("id") int id);

    long countById(@Param("tableName") String tableName, @Param("id") int id);
}