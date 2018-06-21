package com.revengemission.demo.dynamicdatasource.persistence.app.mapper;

import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayStat;
import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayStatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DayStatMapper {
    long countByExample(DayStatExample example);

    int deleteByExample(DayStatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DayStat record);

    int insertSelective(DayStat record);

    List<DayStat> selectByExample(DayStatExample example);

    DayStat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DayStat record, @Param("example") DayStatExample example);

    int updateByExample(@Param("record") DayStat record, @Param("example") DayStatExample example);

    int updateByPrimaryKeySelective(DayStat record);

    int updateByPrimaryKey(DayStat record);
}