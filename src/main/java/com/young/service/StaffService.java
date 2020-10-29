package com.young.service;


import com.github.pagehelper.PageInfo;
import com.young.model.Staff;

public interface StaffService {

	PageInfo<Staff> getStaffList(Integer pageNum);

	int addTime(Staff staff);

	int addStaff(Staff staff);
}
