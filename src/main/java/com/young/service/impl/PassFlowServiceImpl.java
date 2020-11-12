package com.young.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.constant.Const;
import com.young.base.utils.PublicUtils;
import com.young.mapper.ActivityMapper;
import com.young.mapper.BoxMapper;
import com.young.mapper.PassengerFlowNoteMapper;
import com.young.mapper.VipMapper;
import com.young.mapper.VipScoreNoteMapper;
import com.young.model.Activity;
import com.young.model.Box;
import com.young.model.ConsumptionNote;
import com.young.model.PassengerFlowNote;
import com.young.model.Vip;
import com.young.model.VipScoreNote;
import com.young.service.ConsumNoteService;
import com.young.service.PassFlowService;

import jodd.datetime.JDateTime;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("passFlowServiceImpl")
@Transactional
public class PassFlowServiceImpl implements PassFlowService{

	@Autowired
	private PassengerFlowNoteMapper passengerFlowNoteMapper;
	@Autowired
	private ConsumNoteService consumNoteService;
	@Autowired
	private VipMapper vipMapper;
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private VipScoreNoteMapper vipScoreNoteMapper;
	@Autowired
	private BoxMapper boxMapper;

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
		notes.setRemark("入场：每人"+Const.ADMISSION_FEE+"元，"+passengerFlowNote.getPeople()+"人，共计"+notes.getMoney().stripTrailingZeros().toPlainString()+"元");
		consumNoteService.consumption(notes);
		return i;
	}

	@Override
	public Object checkOut(Integer id,String mobile) {
		String text = "";
		if(StringUtil.isNotBlank(mobile)) {
			Vip vip = vipMapper.selectVipByMobile(mobile);
			Long money = this.settleAccounts(id, vip,2);
			VipScoreNote vipScoreNote = new VipScoreNote();
			vipScoreNote.setVipId(vip.getId());
			vipScoreNote.setMobile(vip.getMobile1()+vip.getMobile2());
			vipScoreNote.setType(Const.SCORE_TYPE_GET);
			vipScoreNote.setScore(money);
			vipScoreNote.setTime(new Date());
			int i = vipScoreNoteMapper.insertSelective(vipScoreNote);
			if(i > 0) {
				vip.setScore(vip.getScore().add(new BigDecimal(money)));
				vip.setSumConsume(vip.getSumConsume().add(new BigDecimal(money)));
				BigDecimal now = vip.getNowMoney().subtract(new BigDecimal(money));
				vip.setNowMoney(now);
				vipMapper.updateByPrimaryKeySelective(vip);
				text = "本次共消费:"+money+"元，会员余额："+now.stripTrailingZeros().toPlainString()+"元。";
			}
		}
		PassengerFlowNote note = passengerFlowNoteMapper.selectByPrimaryKey(id);
		note.setStatus(Const.PASS_FLOW_LI_STATUS);
		note.setOffTime(new Date());
		passengerFlowNoteMapper.updateByPrimaryKeySelective(note);
		return text;
	}

	@Override
	public int deductionVoucher(Integer id, BigDecimal money) {
		ConsumptionNote notes = new ConsumptionNote();
		notes.setPassId(id);
		notes.setType(Const.CON_NOTE_SUBTRACT_TYPE);
		notes.setFreeCharge(Const.PUBLIC_NO);
		notes.setMoney(new BigDecimal(0).subtract(money));
		notes.setTime(new Date());
		notes.setRemark("使用抵扣券，抵扣："+money.stripTrailingZeros().toPlainString()+"元");
		return consumNoteService.consumption(notes);
	}

	//计算消费折扣后的总额
	public Long settleAccounts(Integer id, Vip vip, Integer sign) {
		//计算消费总额
		List<ConsumptionNote> NoteList = consumNoteService.selectConsumptionNoteById(id);
		BigDecimal money = new BigDecimal(0);
		for (ConsumptionNote consumptionNote : NoteList) {
			money = money.add(consumptionNote.getMoney());//计算消费
		}
		//计算活动折扣
		List<Activity> activityList = activityMapper.selectActivityNowListByType(Const.ACTIVITY_TYPE_DISCOUNT);
		BigDecimal activityDiscount = new BigDecimal(1);
		if(!CollectionUtils.isEmpty(activityList)) {
			activityDiscount = activityDiscount.multiply(new BigDecimal(activityList.get(0).getNumber()));
		}
		//计算会员折扣
		BigDecimal vipDiscount = new BigDecimal(1);
		if(null != vip) {
			Integer level = vip.getLevel();
			vipDiscount = new BigDecimal(Const.LEVELDISCOUNT.get("lv"+level));
		}
		BigDecimal sumMoney = new BigDecimal(0);
		if(activityDiscount.compareTo(vipDiscount) == -1) {
			sumMoney = money.multiply(activityDiscount);
			if(sign == 2 && activityDiscount.compareTo(new BigDecimal(1)) == -1) {
				ConsumptionNote notes = new ConsumptionNote();
				notes.setPassId(id);
				notes.setType(Const.CON_NOTE_ACT_DISCOUNT_TYPE);
				notes.setFreeCharge(Const.PUBLIC_NO);
				notes.setMoney(money.subtract(sumMoney));
				notes.setTime(new Date());
				notes.setRemark("活动折扣："+Const.DISCOUNT.get(activityDiscount.toString())+"折");
				notes.setBack1(null != vip ? vip.getMobile1()+vip.getMobile2() : null);
				notes.setBack2(activityList.get(0).getName());
				consumNoteService.consumption(notes);
			}
		}else {
			sumMoney = money.multiply(vipDiscount);
			if(sign == 2 && vipDiscount.compareTo(new BigDecimal(1)) == -1) {
				ConsumptionNote notes = new ConsumptionNote();
				notes.setPassId(id);
				notes.setType(Const.CON_NOTE_VIP_DISCOUNT_TYPE);
				notes.setFreeCharge(Const.PUBLIC_NO);
				notes.setMoney(money.subtract(sumMoney));
				notes.setTime(new Date());
				notes.setRemark("会员折扣："+Const.DISCOUNT.get(activityDiscount.toString())+"折");
				notes.setBack1(null != vip ? vip.getMobile1()+vip.getMobile2() : null);
				consumNoteService.consumption(notes);
			}
		}
		return sumMoney.setScale( 0, BigDecimal.ROUND_UP ).longValue();
	}

	@Override
	public Map<String, BigDecimal> selectBoxTypePrice() {
		List<Box> list = boxMapper.selectBoxTypePrice();
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (Box box : list) {
			map.put("type"+box.getType(), box.getPrice());
		}
		return map;
	}

	@Override
	public PassengerFlowNote selectPassengerFlowNoteById(Integer id) {
		return passengerFlowNoteMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addConsumption(ConsumptionNote consumptionNote, Integer people, Integer boxtype) {
		consumptionNote.setFreeCharge(Const.PUBLIC_NO);
		consumptionNote.setTime(new Date());
		if(consumptionNote.getType() == Const.CON_NOTE_RU_TYPE) {
			PassengerFlowNote note = this.selectPassengerFlowNoteById(consumptionNote.getPassId());
			note.setPeople(note.getPeople()+people);
			this.updatePassengerFlowNote(note);
			consumptionNote.setMoney(Const.ADMISSION_FEE.multiply(new BigDecimal(people)));
			consumptionNote.setRemark("入场：每人"+Const.ADMISSION_FEE+"元，"+people+"人，共计"+consumptionNote.getMoney().stripTrailingZeros().toPlainString()+"元");
		}else if(consumptionNote.getType() == Const.CON_NOTE_BOX_TYPE) {
			Map<String, BigDecimal> map = this.selectBoxTypePrice();
			consumptionNote.setMoney(map.get("type"+boxtype).multiply(new BigDecimal(consumptionNote.getBack3())));
			consumptionNote.setRemark("包厢："+map.get("type"+boxtype).stripTrailingZeros().toPlainString()+"元/小时，"+consumptionNote.getBack3()+"小时，共计"+consumptionNote.getMoney().stripTrailingZeros().toPlainString()+"元");
		}else if(consumptionNote.getType() == Const.CON_NOTE_MAID_TYPE) {
			consumptionNote.setMoney(new BigDecimal(consumptionNote.getBack3()).multiply(new BigDecimal(consumptionNote.getBack2())).multiply(Const.MAID_FEE));
			consumptionNote.setRemark("女仆："+Const.MAID_FEE+"元/小时，"+consumptionNote.getBack2()+"位，"+consumptionNote.getBack3()+"小时，共计"+consumptionNote.getMoney().stripTrailingZeros().toPlainString()+"元");
		}else{
			consumptionNote.setRemark((consumptionNote.getRemark()==null?"":consumptionNote.getRemark()+",")+"共计"+consumptionNote.getMoney().stripTrailingZeros().toPlainString()+"元");
		}
		return consumNoteService.consumption(consumptionNote);
	}
	
	public int updatePassengerFlowNote(PassengerFlowNote passengerFlowNote) {
		return passengerFlowNoteMapper.updateByPrimaryKeySelective(passengerFlowNote);
	}
	
	
}
