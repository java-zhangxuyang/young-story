package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.young.base.support.ResponseBo;
import com.young.model.Staff;
import com.young.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@GetMapping("/admin/staff")
	public String staff(Integer pageNum, Model model,HttpServletRequest request) {
		PageInfo<Staff> staffList = staffService.getStaffList(pageNum);
		model.addAttribute("staffs", staffList);
		return "admin_staff";
	}
	
	@PostMapping("/admin/staff/addTime")
	@ResponseBody
	public Object addTime(Staff staff) {
		int i = staffService.addTime(staff);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	@PostMapping("/admin/staff/addStaff")
	@ResponseBody
	public Object addStaff(Staff staff) {
		int i = staffService.addStaff(staff);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
}
