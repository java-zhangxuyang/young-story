package com.young.mapper;

import com.young.model.Staff;
import com.young.model.StaffExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StaffMapper {
    long countByExample(StaffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Staff record);

    int insertSelective(Staff record);

    List<Staff> selectByExample(StaffExample example);

    Staff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
    
    @Select("SELECT * FROM staff WHERE USER_NAME = #{userName}")
	Staff getStaffByUserName(String userName);
}