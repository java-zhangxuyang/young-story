package com.young.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.utils.PublicUtils;
import com.young.mapper.PassengerFlowNoteMapper;
import com.young.model.PassengerFlowNote;
import com.young.model.Vip;
import com.young.service.QueryService;

import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("querySerivceImpl")
public class QuerySerivceImpl implements QueryService {
	
	@Autowired
	private PassengerFlowNoteMapper passengerFlowNoteMapper;
	
	@Override
	public PageInfo<PassengerFlowNote> passengerQuery(Integer pageNum,String startTime, String endTime) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<PassengerFlowNote> list = passengerFlowNoteMapper.passengerQuery(startTime, endTime);
		PageInfo<PassengerFlowNote> page = new PageInfo<>(list);
		return page;
	}
	
	

}
