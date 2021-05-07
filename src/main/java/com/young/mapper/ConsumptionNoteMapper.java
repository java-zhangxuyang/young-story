package com.young.mapper;

import com.young.model.ConsumptionNote;
import com.young.model.ConsumptionNoteExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    
    @Select("select sum(cn.money) from consumption_note cn  where cn.pass_id = #{id} and cn.type = #{type} and cn.back3 = #{back3} ")
    BigDecimal selectSumMoneyByPassIdAndType(Integer id, Integer type, String back3);
    
    @Select("select sum(cn.money) from consumption_note cn  where cn.pass_id = #{id} and cn.type = #{type} ")
    BigDecimal selectRuchangMoneyByPassIdAndType(Integer id, Integer type);

    @Select("select count(0) as boxCount, sum(money) as boxMoney from consumption_note where type = 4 and date_format(time,'%Y-%m') = #{time} ")
    Map<String, Object> getBoxData(String time);
    
    @Select("select count(0) as girlCount, sum(money) as girlMoney from consumption_note where type = 3 and date_format(time,'%Y-%m') = #{time} ")
	Map<String, Object> getgirlData(String time);

    @Select("select a.day,IFNULL(b.box,0) as box from days a left join( SELECT date_format( time,'%d')+0 as times, count(0) as box\r\n" + 
    		" FROM consumption_note where type = 4  and date_format(time,'%Y-%m') = #{time} GROUP BY times) b on a.day = b.times WHERE a.day <= #{day}  ORDER BY a.day")
	List<Map<String, Object>> getChartBoxData(String time, Integer day);

    @Select("select a.day,IFNULL(b.girl,0) as girl from days a left join( SELECT date_format( time,'%d')+0 as times, count(0) as girl\r\n" + 
    		" FROM consumption_note where type = 3  and date_format(time,'%Y-%m') = #{time} GROUP BY times) b on a.day = b.times WHERE a.day <= #{day}  ORDER BY a.day")
	List<Map<String, Object>> getChartGirlData(String time, Integer day);
}