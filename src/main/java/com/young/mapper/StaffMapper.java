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
    
    //获取员工列表关联字典表
    @Select("SELECT staff.*,dict.name as levelName,dic.name as statusName FROM staff " + 
    		"LEFT JOIN dict ON staff.`level` = dict.`no` and dict.`key` = 'staff_level' " + 
    		"LEFT JOIN (SELECT * FROM dict WHERE dict.`key` = 'staff_status') dic ON staff.`status` = dic.`no`" + 
    		"order by staff.status asc,staff.`level` asc")
	List<Staff> selectStaffList();

    //获取员工列表关联字典表
    @Select("SELECT staff.*,dict.name as levelName,dic.name as statusName FROM staff " + 
    		"LEFT JOIN dict ON staff.`level` = dict.`no` and dict.`key` = 'staff_level' " + 
    		"LEFT JOIN (SELECT * FROM dict WHERE dict.`key` = 'staff_status') dic ON staff.`status` = dic.`no` " + 
    		"WHERE staff.user_name is not null "+
    		"order by staff.status asc,staff.`level` asc")
	List<Staff> selectStaffListNoNull();
}