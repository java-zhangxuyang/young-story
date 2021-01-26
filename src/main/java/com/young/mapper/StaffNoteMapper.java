package com.young.mapper;

import com.young.model.StaffNote;
import com.young.model.StaffNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface StaffNoteMapper {
    long countByExample(StaffNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffNote record);

    int insertSelective(StaffNote record);

    List<StaffNote> selectByExample(StaffNoteExample example);

    StaffNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StaffNote record);

    int updateByPrimaryKey(StaffNote record);

    @Select("select * from staff_note where staff_id = #{id} and DATE_FORMAT(time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) order by time ")
	List<StaffNote> selectStaffNoteById(Integer id);
}