package com.revengemission.demo.dynamicdatasource.persistence.h2.mapper;

import com.revengemission.demo.dynamicdatasource.persistence.h2.entity.MonthStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MonthStatMapper {
    long count();

    List<MonthStat> select();

    int insert(@Param("valueDate") String valueDate, @Param("value") String value);
}