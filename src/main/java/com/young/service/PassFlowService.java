package com.young.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.young.model.Box;
import com.young.model.ConsumptionNote;
import com.young.model.PassengerFlowNote;
import com.young.model.Vip;

public interface PassFlowService {

	PageInfo<PassengerFlowNote> selectPassFlowToday(Integer pageNum);

	int addPassFlow(PassengerFlowNote passengerFlowNote);

	Object checkOut(Integer id,String mobile);

	int deductionVoucher(Integer id, BigDecimal money);

	Long settleAccounts(Integer id, Vip vip, Integer sign);

	Map<String, BigDecimal> selectBoxTypePrice();
	
	PassengerFlowNote selectPassengerFlowNoteById(Integer id);

	int addConsumption(ConsumptionNote consumptionNote, Integer people, Integer boxtype);

	List<PassengerFlowNote> getPassFlowList();

	int birthdayBenefits(Integer id, BigDecimal money);

}
