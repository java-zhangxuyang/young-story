package com.young.mapper;

import com.young.model.PassengerFlowNote;
import com.young.model.PassengerFlowNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PassengerFlowNoteMapper {
    long countByExample(PassengerFlowNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PassengerFlowNote record);

    int insertSelective(PassengerFlowNote record);

    List<PassengerFlowNote> selectByExample(PassengerFlowNoteExample example);

    PassengerFlowNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PassengerFlowNote record);

    int updateByPrimaryKey(PassengerFlowNote record);

    @Select("select pfn.*,d.name as statusName from passenger_flow_note pfn left join dict d on pfn.status = d.no where d.key = 'pass_flow_status' and to_days(pfn.to_time) = to_days(now()) order by status desc,off_time asc,number asc")
	List<PassengerFlowNote> selectPassFlowToday();

    @Select("select * from passenger_flow_note where number = #{number} and to_days(to_time) = to_days(now()) ")
	PassengerFlowNote selectTodayByNumber(String number);

    @Select("select * from passenger_flow_note where status = 1 and to_days(to_time) = to_days(now()) ")
	List<PassengerFlowNote> getPassFlowList();
    
    @Update("update passenger_flow_note set back1 = #{newName} where back1 = #{oldName} ")
    int updateNoteNameByName(String oldName, String newName);
    
    @Select("select pfn.*,d.name as statusName from passenger_flow_note pfn left join dict d on pfn.status = d.no where d.key = 'pass_flow_status' and TO_DAYS( NOW( )) - TO_DAYS( pfn.to_time) = 1 order by status desc,off_time asc,number asc")
	List<PassengerFlowNote> selectPassFlowYesterday();
}