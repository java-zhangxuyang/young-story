package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.base.constant.Const;
import com.young.base.controller.BaseController;
import com.young.base.support.ResponseBo;
import com.young.base.utils.IPUtils;
import com.young.model.Staff;
import com.young.service.LoginSerivce;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController extends BaseController {

	@Autowired
	private LoginSerivce loginSerivce;
	
	@GetMapping({ "/", "/admin"})
	public String index(HttpServletRequest request) {
		if(null == request.getSession().getAttribute(Const.LOGIN_SESSION_STAFF));
		return "/admin_login";
	}

	@PostMapping("/login")
	@ResponseBody
	public Object login(String userName, String password, HttpServletRequest request) {
		String ip = IPUtils.getIpAddrByRequest(request);
		Integer error_count = cache.get("ip");
		if (error_count == null || error_count < 3) {
			ResponseBo<Staff> responseBo = loginSerivce.login(userName, password);
			if (responseBo.getCode() == -1) {
				error_count = null == error_count ? 1 : error_count + 1;
				cache.set(ip, error_count, 10 * 60);
			} else {
				Staff staff = responseBo.getPayload();
				log.info(staff.getName()+" 成功登陆系统。");
				request.getSession().setAttribute(Const.LOGIN_SESSION_KEY, "管理员");
				request.getSession().setAttribute(Const.LOGIN_SESSION_STAFF, staff);
				cache.del(ip);
			}
			return responseBo;
		}
		log.error("IP：" + ip + "连续输入密码错误三次，请注意！");
		return ResponseBo.fail("您输入密码已经错误超过3次，请10分钟后尝试");
	}

}
