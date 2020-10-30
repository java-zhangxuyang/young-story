package com.young.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.constant.Const;
import com.young.base.utils.PublicUtils;
import com.young.mapper.StaffMapper;
import com.young.mapper.StaffNoteMapper;
import com.young.model.Staff;
import com.young.model.StaffExample;
import com.young.model.StaffNote;
import com.young.service.StaffService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("staffServiceImpl")
@Transactional
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffMapper staffMapper;
	@Autowired
	private StaffNoteMapper staffNoteMapper;
	
	public PageInfo<Staff> getStaffList(Integer pageNum){
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<Staff> list = staffMapper.selectStaffList();
		PageInfo<Staff> page = new PageInfo<>();
		page.setList(list);
		return page;
	}

	@Override
	public int addTime(Staff staff) {
		Staff sta = staffMapper.selectByPrimaryKey(staff.getId());
		StaffNote note = new StaffNote();
		note.setStaffId(sta.getId());
		note.setStaffName(sta.getName());
		note.setDuration(staff.getBack1());
		note.setTime(new Date());
		staffNoteMapper.insertSelective(note);
		sta.setBack1(sta.getBack1() + staff.getBack1());
		return staffMapper.updateByPrimaryKeySelective(sta);
	}

	@Override
	public int addStaff(Staff staff) {
		staff.setBack1(Const.PUBLIC_NO);
		staff.setStatus(Const.STAFF_ZAI);
		return staffMapper.insertSelective(staff);
	}


	
}
