package com.revengemission.demo.dynamicdatasource.persistence.sqlserver.mapper;

import com.revengemission.demo.dynamicdatasource.persistence.sqlserver.entity.TagIndex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagIndexMapper {
    int count(@Param("tableName") String tableName);

    List<TagIndex> select(@Param("tableName") String tableName);
}