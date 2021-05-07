package com.young.mapper;

import com.young.model.PassengerFlowNote;
import com.young.model.PassengerFlowNoteExample;
import java.util.List;
import java.util.Map;

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

    @Select("select pfn.*,d.name as statusName from passenger_flow_note pfn left join dict d on pfn.status = d.no "
    		+ "where d.key = 'pass_flow_status' and to_days(pfn.to_time) = to_days(now()) "
    		+ "order by status desc,off_time asc,number asc")
	List<PassengerFlowNote> selectPassFlowToday();
    
    @Select("select pfn.*,d.name as statusName from passenger_flow_note pfn left join dict d on pfn.status = d.no \r\n" + 
    		"where d.key = 'pass_flow_status' and ((TO_DAYS( NOW( )) - TO_DAYS( pfn.to_time) = 1 and status = 1) or \r\n" + 
    		"(to_days(pfn.to_time) = to_days(now())))\r\n" + 
    		"order by status desc,off_time asc,number asc")
    List<PassengerFlowNote> selectPassFlowTodayOrYesterday();

    @Select("select * from passenger_flow_note where number = #{number} and TO_DAYS(NOW( ) ) - TO_DAYS(to_time) <= 1 ")
	PassengerFlowNote selectTodayByNumber(String number);

    @Select("select * from passenger_flow_note where status = 1 and TO_DAYS(NOW( ) ) - TO_DAYS(to_time) <= 1 order by status desc,off_time asc,number asc ")
	List<PassengerFlowNote> getPassFlowList();
    
    @Update("update passenger_flow_note set back1 = #{newName} where back1 = #{oldName} ")
    int updateNoteNameByName(String oldName, String newName);
    
    @Select("select pfn.*,d.name as statusName from passenger_flow_note pfn left join dict d on pfn.status = d.no where d.key = 'pass_flow_status' and TO_DAYS( NOW( )) - TO_DAYS( pfn.to_time) = 1 order by status desc,off_time asc,number asc")
	List<PassengerFlowNote> selectPassFlowYesterday();
    
    @Select("select pfn.*,d.name as statusName from passenger_flow_note pfn left join dict d on pfn.status = d.no "
    	      + " where d.key = 'pass_flow_status' and date_format(pfn.to_time,'%y%m%d') >= date_format(#{startTime},'%y%m%d') "
    	      + " and date_format(pfn.to_time,'%y%m%d') <= date_format(#{endTime},'%y%m%d') order by pfn.to_time desc")
    	 List<PassengerFlowNote> passengerQuery(String startTime, String endTime);

    @Select("select sum(back2) as sumMoney,sum(people) as sumPeople,round(sum(back2)/sum(people),2) as avgMoney,count(0) as batch,round(sum(back2)/count(0),2) as avgBatch\r\n" + 
    		"from passenger_flow_note where 1 = 1 and date_format(to_time,'%Y-%m') = #{toTime}")
	Map<String, Object> getTableData1(String toTime);

    @Select("select sum(back2) as ptMoney from passenger_flow_note where back3 is null and date_format(to_time,'%Y-%m') = #{toTime} ")
    Map<String, Object> getTableDataPt(String toTime);
    
    @Select("select sum(back2) as vipMoney from passenger_flow_note where back3 is not null and date_format(to_time,'%Y-%m') = #{toTime} ")
	Map<String, Object> getTableDataVip(String toTime);

    @Select("select a.day,IFNULL(b.money,0) as money,IFNULL(b.people,0) as people,IFNULL(b.batch,0) as batch from days a left join(SELECT date_format( to_time,'%d')+0 as times, sum(back2) as money,sum(people) as people,count(0) as batch\r\n" + 
    		" FROM passenger_flow_note WHERE 1=1 and date_format(to_time,'%Y-%m') = #{toTime} GROUP BY times) b on a.day = b.times WHERE a.day <= #{day}  ORDER BY a.day" )
    List<Map<String, Object>> getChartData(String toTime,Integer day);
}