package com.young.service;

import com.github.pagehelper.PageInfo;
import com.young.model.PassengerFlowNote;

public interface PassFlowService {

	PageInfo<PassengerFlowNote> selectPassFlowToday(Integer pageNum);

	int addPassFlow(PassengerFlowNote passengerFlowNote);

	int checkOut(Integer id,Integer discount);

}
