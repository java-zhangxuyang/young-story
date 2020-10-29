package com.young.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.constant.Const;
import com.young.base.utils.PublicUtils;
import com.young.mapper.StaffMapper;
import com.young.model.Staff;
import com.young.model.StaffExample;
import com.young.service.StaffService;
import com.young.service.VipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("vipServiceImpl")
public class VipServiceImpl implements VipService{

	@Autowired
	private StaffMapper staffMapper;

	
}
