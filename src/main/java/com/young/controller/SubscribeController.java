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
import com.young.model.BoxSubscribeNote;
import com.young.service.SubscribeService;

@Controller
@RequestMapping("/admin/subscribe")
public class SubscribeController {

	@Autowired
	private SubscribeService subscribeService;
	
	/*
	 * 预约列表界面
	 */
	@GetMapping("")
	public String subscribe(Integer pageNum, Model model,HttpServletRequest request) {
		PageInfo<BoxSubscribeNote> subscribeList = subscribeService.getBoxSubscribeNoteList(pageNum);
		model.addAttribute("notes", subscribeList);
		return "admin_subscribe";
	}
	
	/*
	 * 修改预约状态
	 */
	@PostMapping("/updateSubscribeStatus")
	@ResponseBody
	public Object updateSubscribeStatus(Integer id, Integer status) {
		int i = subscribeService.updateSubscribeStatus(id,status);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	
}
