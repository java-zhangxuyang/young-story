package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.pagehelper.PageInfo;
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
}
