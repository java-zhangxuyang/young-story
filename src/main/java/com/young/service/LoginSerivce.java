package com.young.service;


import com.young.base.support.ResponseBo;
import com.young.model.Staff;

public interface LoginSerivce {

	ResponseBo<Staff> login(String userName, String password);


}
