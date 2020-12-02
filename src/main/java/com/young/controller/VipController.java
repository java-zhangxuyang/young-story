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

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.young.base.support.ResponseBo;
import com.young.model.Flushing;
import com.young.model.Staff;
import com.young.model.Vip;
import com.young.service.StaffService;
import com.young.service.VipService;

@Controller
@RequestMapping("/admin/vip")
public class VipController {

	@Autowired
	private VipService vipService;
	@Autowired
	private StaffService staffService;
	
	/*
	 * 会员界面
	 */
	@GetMapping("")
	public String vip(Integer pageNum, String mobile, String vipName, Model model,HttpServletRequest request) {
		PageInfo<Vip> vipList = vipService.getVipList(pageNum,mobile,vipName);
		model.addAttribute("vips", vipList);
		List<Flushing> flushList = vipService.getFlushingList();
		List<Staff> staffList = staffService.getStaffList();
		model.addAttribute("flushs", JSONArray.toJSON(flushList));
		model.addAttribute("staffList", JSONArray.toJSON(staffList));
		model.addAttribute("mobile", mobile);
		model.addAttribute("vipName", vipName);
		return "admin_vip";
	}
	
	/*
	 * 为会员充值
	 */
	@PostMapping("/vipRecharge")
	@ResponseBody
	public Object vipRecharge(Vip vip) {
		int i = vipService.vipRecharge(vip);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	/*
	 * 添加新会员
	 */
	@PostMapping("/addVip")
	@ResponseBody
	public Object addVip(Vip vip) {
		int i = vipService.addVip(vip);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	/*
	 * 消耗积分提升会员
	 */
	@PostMapping("/vipLevelUpgrade")
	@ResponseBody
	public ResponseBo vipLevelUpgrade(Integer id) {
		ResponseBo ob = vipService.vipLevelUpgrade(id);
		if(ob.isSuccess()) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
}
