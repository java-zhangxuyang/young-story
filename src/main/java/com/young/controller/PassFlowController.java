package com.young.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.base.constant.Const;
import com.young.base.support.ResponseBo;
import com.young.model.Box;
import com.young.model.ConsumptionNote;
import com.young.model.PassengerFlowNote;
import com.young.model.Vip;
import com.young.service.ConsumNoteService;
import com.young.service.PassFlowService;
import com.young.service.VipService;

import jodd.util.StringUtil;

@Controller
@RequestMapping("/admin/passFlow")
public class PassFlowController {
	
	@Autowired
	private PassFlowService passFlowService;
	@Autowired
	private ConsumNoteService consumNoteService;
	@Autowired
	private VipService vipService;
	
	//添加客流
	@ResponseBody
	@PostMapping("/addPassFlow")
	private Object addPassFlow(PassengerFlowNote passengerFlowNote) {
		if(passengerFlowNote.getPeople() == null || passengerFlowNote.getPeople() <= 0) {
			return ResponseBo.fail("请输入正确的人数！");
		}
		int i = passFlowService.addPassFlow(passengerFlowNote);
		if(i > 0 ) {
			return ResponseBo.ok();
		}
		return ResponseBo.fail();
	}
	
	//添加消费
	@ResponseBody
	@PostMapping("/addConsumption")
	private Object addConsumption(ConsumptionNote consumptionNote,Integer people,Integer boxtype) {
		if(consumptionNote.getMoney() == null || consumptionNote.getType() == null) {
			return ResponseBo.fail("填写有误，请检查后重试！");
		}
		passFlowService.addConsumption(consumptionNote,people,boxtype);
		return ResponseBo.ok();
	}
	
	//根据ID查询消费清单
	@ResponseBody
	@PostMapping("/selectConsumptionNoteById")
	private Object selectConsumptionNoteById(Integer id) {
		if(id == null) {
			return ResponseBo.fail("请检查后重试！");
		}
		List<ConsumptionNote> list = consumNoteService.selectConsumptionNoteByPassId(id);
		return ResponseBo.ok(list);
	}
	
	//计算消费
	@ResponseBody
	@PostMapping("/settleAccounts")
	private Object settleAccounts(Integer id,String mobile) {
		if(id == null) {
			return ResponseBo.fail("填写有误，请检查后重试！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Vip vip = null;
		if(StringUtil.isNotBlank(mobile)) {
			vip = vipService.getVipByMobile(mobile);
			if(null != vip) {
				Long sumMoney = passFlowService.settleAccounts(id,vip,1);
				map.put("sumMoney", sumMoney);
				map.put("surMoney", vip.getNowMoney().subtract(new BigDecimal(sumMoney)).stripTrailingZeros().toPlainString());
				map.put("vipMoney", vip.getNowMoney().stripTrailingZeros().toPlainString());
			}else {
				return ResponseBo.fail("该会员不存在！");
			}
		}else {
			Long sumMoney = passFlowService.settleAccounts(id,vip,1);
			map.put("sumMoney", sumMoney);
		}
		return ResponseBo.ok(map);
	}
	
	
	//结账离场
	@ResponseBody
	@PostMapping("/checkOut")
	private Object checkOut(Integer id,String mobile) {
		if(id == null) {
			return ResponseBo.fail("填写有误，请检查后重试！");
		} 
		Object result = passFlowService.checkOut(id,mobile);
		return ResponseBo.ok(result);
	}
	
	//抵扣券
	@ResponseBody
	@PostMapping("/deductionVoucher")
	private Object deductionVoucher(Integer id,BigDecimal money) {
		if(id == null) {
			return ResponseBo.fail("填写有误，请检查后重试！");
		}
		int i = passFlowService.deductionVoucher(id,money);
		if(i > 0 ) {
			return ResponseBo.ok();
		}
		return ResponseBo.fail();
	}
	
	//生日福利
	@ResponseBody
	@PostMapping("/birthdayBenefits")
	private Object birthdayBenefits(Integer id,BigDecimal money) {
		if(id == null) {
			return ResponseBo.fail("填写有误，请检查后重试！");
		}
		int i = passFlowService.birthdayBenefits(id,money);
		if(i > 0 ) {
			return ResponseBo.ok();
		}
		return ResponseBo.fail();
	}
	
	//获取号码牌列表
	@ResponseBody
	@PostMapping("/getPassFlowList")
	private Object getPassFlowList() {
		List<PassengerFlowNote> List = passFlowService.getPassFlowList();
		return ResponseBo.ok(List);
	}

}
