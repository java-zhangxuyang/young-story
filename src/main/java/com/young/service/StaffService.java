package com.young.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.young.model.Staff;

public interface StaffService {

	PageInfo<Staff> getStaffList(Integer pageNum);

	int addTime(Staff staff);

	int addStaff(Staff staff);

	int addUserName(Staff staff);
	
	Staff getStaffByUserName(String userName);

	List<Staff> getStaffList();
}
