package com.young.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.constant.Const;
import com.young.base.support.ResponseBo;
import com.young.base.utils.PublicUtils;
import com.young.mapper.BoxMapper;
import com.young.mapper.DictMapper;
import com.young.mapper.PassengerFlowNoteMapper;
import com.young.model.Box;
import com.young.model.BoxExample;
import com.young.model.ConsumptionNote;
import com.young.model.PassengerFlowNote;
import com.young.service.ConsumNoteService;
import com.young.service.PassFlowService;

import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("passFlowServiceImpl")
public class PassFlowServiceImpl implements PassFlowService{

	@Autowired
	private PassengerFlowNoteMapper passengerFlowNoteMapper;
	@Autowired
	private ConsumNoteService consumNoteService;

	@Override
	public PageInfo<PassengerFlowNote> selectPassFlowToday(Integer pageNum) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<PassengerFlowNote> passList = passengerFlowNoteMapper.selectPassFlowToday();
		PageInfo<PassengerFlowNote> page = new PageInfo<PassengerFlowNote>(passList);
		return page;
	}

	@Override
	public int addPassFlow(PassengerFlowNote passengerFlowNote) {
		JDateTime now = new JDateTime();
		Integer number = Integer.parseInt(now.getDay() + "" +now.getHour() +"" +now.getMinute()+""+ PublicUtils.getRandom());
		PassengerFlowNote passFlow = passengerFlowNoteMapper.selectTodayByNumber(number);
		if(null != passFlow) {
			number = Integer.parseInt(now.getDay() + "" +now.getHour() + "" + now.getMinute() + "" + now.getSecond() );
		}
		passengerFlowNote.setNumber(number);
		passengerFlowNote.setToTime(now.subSecond(20).convertToDate());
		passengerFlowNote.setStatus(Const.PASS_FLOW_ZAI_STATUS);
		int i = passengerFlowNoteMapper.insertSelective(passengerFlowNote);
		ConsumptionNote notes = new ConsumptionNote();
		notes.setPassId(passengerFlowNote.getId());
		notes.setType(Const.CON_NOTE_RU_TYPE);
		notes.setFreeCharge(Const.PUBLIC_NO);
		notes.setMoney(new BigDecimal(passengerFlowNote.getPeople()).multiply(Const.ADMISSION_FEE));
		notes.setTime(now.convertToDate());
		notes.setRemark("入场费：每人"+Const.ADMISSION_FEE+"元，"+passengerFlowNote.getPeople()+"人，共计"+new BigDecimal(passengerFlowNote.getPeople()).multiply(Const.ADMISSION_FEE));
		consumNoteService.consumption(notes);
		return i;
	}

	@Override
	public int checkOut(Integer id,Integer discount) {
		if(null != discount) {
			List<ConsumptionNote> list = consumNoteService.selectConsumptionNoteById(id);
			BigDecimal money = new BigDecimal(0);
			for (ConsumptionNote consumptionNote : list) {
				if(consumptionNote.getType() != Const.CON_NOTE_MAID_TYPE && consumptionNote.getType() != Const.CON_NOTE_DISCOUNT_TYPE) {
					money = money.add(consumptionNote.getMoney());
				}
			}
			
			ConsumptionNote notes = new ConsumptionNote();
			notes.setPassId(id);
			notes.setType(Const.CON_NOTE_DISCOUNT_TYPE);
			notes.setFreeCharge(Const.PUBLIC_NO);
			notes.setMoney(new BigDecimal(Math.floor(new BigDecimal(discount).multiply(new BigDecimal(0.01)).multiply(money).doubleValue())).subtract(money));
			notes.setTime(new Date());
			notes.setRemark("使用折扣："+discount+"折");
			consumNoteService.consumption(notes);
		}
		PassengerFlowNote note = passengerFlowNoteMapper.selectByPrimaryKey(id);
		note.setStatus(Const.PASS_FLOW_LI_STATUS);
		return passengerFlowNoteMapper.updateByPrimaryKeySelective(note);
	}
	
	
	
}
