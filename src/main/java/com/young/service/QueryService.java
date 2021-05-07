package com.young.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.young.model.PassengerFlowNote;

public interface QueryService {

	PageInfo<PassengerFlowNote> passengerQuery(Integer pageNum,String startTime, String endTime);

	Map<String, Object> getTableData(String date);

	
}
