package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.young.base.support.ResponseBo;
import com.young.model.Staff;
import com.young.service.StaffService;

@Controller
public class VipController {

	@Autowired
	private StaffService staffService;
	
}
