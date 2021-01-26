package com.young.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.young.model.PassengerFlowNote;

public interface QueryService {

	PageInfo<PassengerFlowNote> passengerQuery(Integer pageNum,String startTime, String endTime);

	
}
