package com.young.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.young.service.QueryService;

@Controller
@RequestMapping("/admin/query")
public class QueryController {

	@Autowired
	private QueryService queryService;
	
	@GetMapping("/passenger")
	public String passenger(String sign, Model model,HttpServletRequest request) {
		Map<String, Object> map = queryService.passengerQuery(sign);
		model.addAttribute("map", map);
		return "admin_passenger";
	}
	
	@GetMapping("/revenue")
	public String revenue(Model model,HttpServletRequest request) {
		
		return "admin_revenue";
	}
	
	
}
