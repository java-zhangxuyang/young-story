package com.young.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.young.base.constant.Const;
import com.young.base.support.ResponseBo;
import com.young.mapper.StaffMapper;
import com.young.model.Staff;
import com.young.service.LoginService;

import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("loginSerivceImpl")
public class LoginSerivceImpl implements LoginService {

	@Autowired
	private StaffMapper staffMapper;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResponseBo<Staff> login(String userName, String password) {
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			return ResponseBo.fail("用户名和密码不能为空");
		}

		/* 123
		 * Staff staff = staffMapper.getStaffByUserName(userName); if (null == staff) {
		 * log.debug("登陆帐号错误，帐号：" + userName + "，密码：" + password); return
		 * ResponseBo.fail("用户名或密码错误！"); }
		 */
		String realUserName = Const.LOGIN_ACCOUNTS_SALT;
		if (!realUserName.equalsIgnoreCase(userName)) {
			log.debug("登陆帐号错误，帐号：" + userName + "，密码：" + password);
			return ResponseBo.fail("用户名或密码错误！");
		}
		String realpassword = Const.LOGIN_PASSWORD_SALT + new JDateTime().toString("YYYYMMDD");
		//String encodeStr = DigestUtils.md5DigestAsHex(md5.getBytes());

		if (realpassword.equals(password)) {
			return ResponseBo.ok();
		}
		ResponseBo responseBo = ResponseBo.fail(-1);
		responseBo.setMsg("用户名或密码错误！");
		return responseBo;
	}

	
	/*
	 * public static void main(String[] args) { String realUserName =
	 * Const.LOGIN_ACCOUNTS_SALT + new JDateTime().toString("DD"); //String
	 * encodeStr=DigestUtils.md5DigestAsHex(md5.getBytes());
	 * System.out.println(realUserName); }
	 */
	

}
