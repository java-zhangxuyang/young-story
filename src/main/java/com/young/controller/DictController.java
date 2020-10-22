package com.young.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.model.Dict;
import com.young.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController {
	
	@Autowired
	private DictService dictService;
	
	/*
	 * 根据key获取字典
	 */
	@ResponseBody
	@RequestMapping(value = "getDictList")
	public List<Dict> getDictList(String key){
		List<Dict> list =  dictService.getDictList(key);
		return list;
	}

}
