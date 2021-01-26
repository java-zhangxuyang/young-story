package com.young.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.young.base.support.ResponseBo;
import com.young.model.Coupon;
import com.young.model.Staff;
import com.young.model.StaffNote;
import com.young.service.StaffService;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@GetMapping("")
	public String staff(Integer pageNum, Model model,HttpServletRequest request) {
		PageInfo<Staff> staffList = staffService.getStaffList(pageNum);
		model.addAttribute("staffs", staffList);
		return "admin_staff";
	}
	
	
	/*
	 * 为员工添加接单时长
	 */
	@PostMapping("/addTime")
	@ResponseBody
	public Object addTime(Staff staff) {
		int i = staffService.addTime(staff);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	/*
	 * 修改员工昵称
	 */
	@PostMapping("/addUserName")
	@ResponseBody
	public Object addUserName(Staff staff) {
		Staff sta = staffService.getStaffByUserName(staff.getUserName());
		if(null != sta) {
			return ResponseBo.fail("该昵称已存在！");
		}else {
			int i = staffService.addUserName(staff);
			if(i > 0) {
				return ResponseBo.ok();
			}else {
				return ResponseBo.fail();
			}
		}
	}
	
	/*
	 * 添加新员工
	 */
	@PostMapping("/addStaff")
	@ResponseBody
	public Object addStaff(Staff staff) {
		int i = staffService.addStaff(staff);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	/*
	 * 查询员工列表
	 */
	@PostMapping("/getStaffList")
	@ResponseBody
	public Object getStaffList() {
		List<Staff> list = staffService.getStaffList();
		return ResponseBo.ok(list);
	}
	
	/*
	 * 查询员工列表
	 */
	@PostMapping("/selectStaffNoteById")
	@ResponseBody
	public Object selectStaffNoteById(Integer id) {
		List<StaffNote> list = staffService.selectStaffNoteById(id);
		return ResponseBo.ok(list);
	}
}
