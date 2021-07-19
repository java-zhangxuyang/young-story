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
import com.young.model.ConsumptionNote;
import com.young.model.Coupon;
import com.young.model.PassengerFlowNote;
import com.young.service.CouponService;

import jodd.datetime.JDateTime;

@Controller
@RequestMapping("/admin/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	/*
	 * 优惠项列表界面
	 */
	@GetMapping("")
	public String coupon(Integer pageNum, Model model,HttpServletRequest request) {
		PageInfo<Coupon> couponList = couponService.getCouponList(pageNum);
		model.addAttribute("coupons", couponList);
		return "admin_coupon";
	}
	
	/*
	 * 添加优惠项
	 */
	@PostMapping("/addCoupon")
	@ResponseBody
	public Object addCoupon(Coupon coupon) {
		int i = couponService.addCoupon(coupon);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	/*
	 * 查询优惠项
	 */
	@PostMapping("/getCoupondkqList")
	@ResponseBody
	public Object getCoupondkqList(Integer type) {
		List<Coupon> list = couponService.getCoupondkqList(type);
		return ResponseBo.ok(list);
	}
	
	/*
	 * 删除优惠项
	 */
	@PostMapping("/deleteCoupon")
	@ResponseBody
	public Object deleteCoupon(Integer id) {
		int i = couponService.deleteCoupon(id);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	/*
	 * 去查看消费管理
	 */
	@GetMapping("/consumptionQuery")
	public String consumptionQuery(Integer pageNum, String startTime, String endTime, String type, Model model,HttpServletRequest request) {
		String now = new JDateTime().toString("YYYY-MM-DD");
		String day7 = new JDateTime().subDay(7).toString("YYYY-MM-DD");
		if(null == startTime) {
			startTime = day7;
		}
		if(null == endTime) {
			endTime = now;
		}
		PageInfo<ConsumptionNote> conList = couponService.consumptionQuery(pageNum, startTime, endTime, type);
		model.addAttribute("conLists", conList);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "admin_conList";
	}
}
