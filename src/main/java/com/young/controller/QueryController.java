package com.young.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.young.model.PassengerFlowNote;
import com.young.service.QueryService;

import jodd.datetime.JDateTime;

@Controller
@RequestMapping("/admin/query")
public class QueryController {

	@Autowired
	private QueryService queryService;
	
	@GetMapping("/passenger")
	public String passenger(Integer pageNum, String startTime, String endTime, Model model,HttpServletRequest request) {
		String now = new JDateTime().toString("YYYY-MM-DD");
		String day7 = new JDateTime().subDay(7).toString("YYYY-MM-DD");
		if(null == startTime) {
			startTime = day7;
		}
		if(null == endTime) {
			endTime = now;
		}
		PageInfo<PassengerFlowNote> passList = queryService.passengerQuery(pageNum, startTime, endTime);
		model.addAttribute("passFlows", passList);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "admin_passenger";
	}
	
	@GetMapping("/revenue")
	public String revenue(Model model,HttpServletRequest request) {
		
		return "admin_revenue";
	}
	
	
}
