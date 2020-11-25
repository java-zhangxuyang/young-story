package com.young.mapper;

import com.young.model.ConsumptionNote;
import com.young.model.ConsumptionNoteExample;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select cn.*,d.name as typeName from consumption_note cn LEFT JOIN dict d on cn.type = d.`no` where cn.pass_id = #{id} and d.`key`='con_note_type' order by  time asc ")
	List<ConsumptionNote> selectConsumptionNoteByPassId(Integer id);
    
    @Select("select sum(cn.money) from consumption_note cn  where cn.pass_id = #{id} and cn.type = 2 and cn.back3 = #{back3} order by  cn.time asc ")
    BigDecimal selectSumMoneyByPassIdAndType(Integer id, String back3);
}