package com.revengemission.demo.dynamicdatasource.persistence.app.mapper;

import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayTaskResult;
import com.revengemission.demo.dynamicdatasource.persistence.app.entity.DayTaskResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DayTaskResultMapper {
    long countByExample(DayTaskResultExample example);

    int deleteByExample(DayTaskResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DayTaskResult record);

    int insertSelective(DayTaskResult record);

    List<DayTaskResult> selectByExample(DayTaskResultExample example);

    DayTaskResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DayTaskResult record, @Param("example") DayTaskResultExample example);

    int updateByExample(@Param("record") DayTaskResult record, @Param("example") DayTaskResultExample example);

    int updateByPrimaryKeySelective(DayTaskResult record);

    int updateByPrimaryKey(DayTaskResult record);
}