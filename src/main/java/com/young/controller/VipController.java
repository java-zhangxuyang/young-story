package com.young.controller;

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
import com.young.model.Staff;
import com.young.model.Vip;
import com.young.service.StaffService;
import com.young.service.VipService;

@Controller
@RequestMapping("/admin/vip")
public class VipController {

	@Autowired
	private VipService vipService;
	
	/*
	 * 会员界面
	 */
	@GetMapping("")
	public String vip(Integer pageNum, Model model,HttpServletRequest request) {
		PageInfo<Vip> vipList = vipService.getVipList(pageNum);
		model.addAttribute("vips", vipList);
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
	
}
