package com.young.mapper;

import com.young.model.ConsumptionNote;
import com.young.model.ConsumptionNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumptionNoteMapper {
    long countByExample(ConsumptionNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConsumptionNote record);

    int insertSelective(ConsumptionNote record);

    List<ConsumptionNote> selectByExample(ConsumptionNoteExample example);

    ConsumptionNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsumptionNote record);

    int updateByPrimaryKey(ConsumptionNote record);
}