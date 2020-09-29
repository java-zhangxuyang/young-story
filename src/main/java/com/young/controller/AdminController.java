package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.base.constant.Const;
import com.young.base.support.ResponseBo;
import com.young.model.Staff;
import com.young.service.LoginSerivce;

import jodd.datetime.JDateTime;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private LoginSerivce loginSerivce;
	/**
	 * 后端管理主页
	 */
	@GetMapping("/index")
	public String index(Model model,HttpServletRequest request) {
		Staff staff = (Staff)request.getSession().getAttribute(Const.LOGIN_SESSION_STAFF);
		if(null == staff) {
			return "/admin";
		}
		String date = new JDateTime().toString("YYYY-MM");
		model.addAttribute("staff", staff);
		model.addAttribute("new_time", date);
		return "/admin_index";
	}
	
	/**
	 * 后端管理推出登陆
	 */
	@PostMapping("/loginOut")
	@ResponseBody
	public ResponseBo loginOut(HttpServletRequest request) {
		request.getSession().removeAttribute(Const.LOGIN_SESSION_KEY);
		request.getSession().removeAttribute(Const.LOGIN_SESSION_STAFF);
        return ResponseBo.ok();
	}
}
