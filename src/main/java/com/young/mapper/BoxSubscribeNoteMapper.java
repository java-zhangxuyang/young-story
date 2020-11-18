package com.young.mapper;

import com.young.model.BoxSubscribeNote;
import com.young.model.BoxSubscribeNoteExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BoxSubscribeNoteMapper {
    long countByExample(BoxSubscribeNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoxSubscribeNote record);

    int insertSelective(BoxSubscribeNote record);

    List<BoxSubscribeNote> selectByExample(BoxSubscribeNoteExample example);

    BoxSubscribeNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoxSubscribeNote record);

    int updateByPrimaryKey(BoxSubscribeNote record);
    
    @Select("select max(bsn.to_time) from box_subscribe_note bsn where bsn.box_id = #{boxId}  and to_days(bsn.to_time) = to_days(now())")
    Date selectMaxDateToday(Integer boxId);
    
    @Select("select max(bsn.to_time) from box_subscribe_note bsn where bsn.box_id = #{boxId}  and to_days(bsn.to_time) = to_days(now()) and id <> #{id} ")
    Date selectMaxDateTodayNoId(Integer boxId, Integer id);
    
    @Select("select * from box_subscribe_note bsn where to_days(bsn.to_time) = to_days(now()) order by bsn.to_time ")
    List<BoxSubscribeNote> selectSubscribeToday();

    @Select("select bsn.*,d.name as statusName,b.name as boxName from box_subscribe_note bsn left join box b on bsn.box_id = b.id LEFT JOIN dict d on bsn.`status`=d.no where d.key='box_note_status' order by bsn.to_time desc")
	List<BoxSubscribeNote> selectNoteList();
}