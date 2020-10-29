package com.young.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.base.constant.Const;
import com.young.base.support.ResponseBo;
import com.young.model.ConsumptionNote;
import com.young.model.PassengerFlowNote;
import com.young.service.ConsumNoteService;
import com.young.service.PassFlowService;

@Controller
@RequestMapping("/admin/passFlow")
public class PassFlowController {
	
	@Autowired
	private PassFlowService passFlowService;
	@Autowired
	private ConsumNoteService consumNoteService;
	
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
	
	//添加女仆消费
	@ResponseBody
	@PostMapping("/addConsumption")
	private Object addConsumption(ConsumptionNote consumptionNote) {
		if(consumptionNote.getMoney() == null || consumptionNote.getType() == null) {
			return ResponseBo.fail("填写有误，请检查后重试！");
		}
		consumptionNote.setFreeCharge(Const.PUBLIC_NO);
		consumptionNote.setTime(new Date());
		consumptionNote.setRemark("女仆费：每位"+Const.MAID_FEE+"元/小时，"+consumptionNote.getBack2()+"位，"+consumptionNote.getBack3()+"小时，共计"+new BigDecimal(consumptionNote.getBack3()).multiply(new BigDecimal(consumptionNote.getBack2())).multiply(Const.MAID_FEE));
		int i = consumNoteService.consumption(consumptionNote);
		if(i > 0 ) {
			return ResponseBo.ok();
		}
		return ResponseBo.fail();
	}
	
	//根据ID查询消费清单
	@ResponseBody
	@PostMapping("/selectConsumptionNoteById")
	private Object selectConsumptionNoteById(Integer id) {
		if(id == null) {
			return ResponseBo.fail("请检查后重试！");
		}
		List<ConsumptionNote> list = consumNoteService.selectConsumptionNoteById(id);
		return ResponseBo.ok(list);
	}
	
	//结账离场
	@ResponseBody
	@PostMapping("/checkOut")
	private Object checkOut(Integer id,Integer discount) {
		if(id == null) {
			return ResponseBo.fail("填写有误，请检查后重试！");
		}
		int i = passFlowService.checkOut(id,discount);
		if(i > 0 ) {
			return ResponseBo.ok();
		}
		return ResponseBo.fail();
	}

}
