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
import com.young.mapper.ConsumptionNoteMapper;
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
	@Autowired
	private ConsumptionNoteMapper consumptionNoteMapper;

	@Override
	public PageInfo<PassengerFlowNote> selectPassFlowToday(Integer pageNum) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<PassengerFlowNote> passList = new ArrayList<>();
		JDateTime now = new JDateTime();
		if(now.getHour() > 6) {
			passList = passengerFlowNoteMapper.selectPassFlowToday();
		}else {
			passList = passengerFlowNoteMapper.selectPassFlowYesterday();
		}
		PageInfo<PassengerFlowNote> page = new PageInfo<PassengerFlowNote>(passList);
		return page;
	}

	@Override
	public int addPassFlow(PassengerFlowNote passengerFlowNote) {
		JDateTime now = new JDateTime();
		String minute = "0";
		if(now.getMinute() < 10) {
			minute += now.getMinute();
		}else {
			minute = now.getMinute()+""; 
		}
		String number = now.getDay() + " " +now.getHour() +":" +minute+"-"+ PublicUtils.getRandom();
		PassengerFlowNote passFlow = passengerFlowNoteMapper.selectTodayByNumber(number);
		if(null != passFlow) {
			number = now.getDay() + " " +now.getHour() + ":" + minute + "-" + now.getSecond();
		}
		passengerFlowNote.setNumber(number);
		passengerFlowNote.setToTime(now.convertToDate());
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
		String text = "非会员~手工结账成功！";
		PassengerFlowNote note = passengerFlowNoteMapper.selectByPrimaryKey(id);
		if(StringUtil.isNotBlank(mobile)) {//会员结账
			Vip vip = vipMapper.selectVipByMobile(mobile);
			Long money = this.settleAccounts(id, vip,2);
			/*
			 * if(StringUtil.isNotBlank(vip.getBack1())) { note.setBack1(vip.getBack1()); }
			 */
			note.setBack2(money+"");
			note.setBack3(mobile);
			VipScoreNote vipScoreNote = new VipScoreNote();
			vipScoreNote.setVipId(vip.getId());
			vipScoreNote.setMobile(vip.getMobile1()+vip.getMobile2());
			vipScoreNote.setType(Const.SCORE_TYPE_GET);
			vipScoreNote.setScore(money);
			vipScoreNote.setTime(new Date());
			int i = vipScoreNoteMapper.insertSelective(vipScoreNote);
			if(i > 0) {
				BigDecimal thismoney = new BigDecimal(money);
				vip.setScore(vip.getScore().add(thismoney));
				vip.setSumConsume(vip.getSumConsume().add(thismoney));
				BigDecimal big0 = new BigDecimal(0);
				if(vip.getNowMoney().compareTo(big0) < 1) {
					vip.setNowMoney(big0);
					text = "手工结账~~本次共消费："+money+"元，会员余额：0元。";
				}else {
					BigDecimal now = vip.getNowMoney().subtract(thismoney);
					if(now.compareTo(big0) < 1) {
						vip.setNowMoney(big0);
					}else {
						vip.setNowMoney(now);
					}
					text = "本次共消费:"+money+"元，会员余额："+now.stripTrailingZeros().toPlainString()+"元。";
				}
				vipMapper.updateByPrimaryKeySelective(vip);
			}
		}else {//普通结账
			Long money = this.settleAccounts(id, null,2);
			note.setBack2(money+"");
		}
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
		List<ConsumptionNote> NoteList = consumNoteService.selectConsumptionNoteByPassId(id);
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
		Long sumMoney = 0l;
		if(activityDiscount.compareTo(vipDiscount) == -1) {
			sumMoney = money.multiply(activityDiscount).setScale( 0, BigDecimal.ROUND_DOWN ).longValue();
			if(sign == 2 && activityDiscount.compareTo(new BigDecimal(1)) == -1) {
				ConsumptionNote notes = new ConsumptionNote();
				notes.setPassId(id);
				notes.setType(Const.CON_NOTE_ACT_DISCOUNT_TYPE);
				notes.setFreeCharge(Const.PUBLIC_NO);
				notes.setMoney(new BigDecimal(0).subtract(money.subtract(new BigDecimal(sumMoney))));
				notes.setTime(new Date());
				notes.setRemark("活动折扣："+Const.DISCOUNT.get(activityDiscount.toString())+"折");
				notes.setBack1(null != vip ? vip.getMobile1()+vip.getMobile2() : null);
				notes.setBack2(activityList.get(0).getName());
				consumNoteService.consumption(notes);
			}
		}else {
			sumMoney = money.multiply(vipDiscount).setScale( 0, BigDecimal.ROUND_DOWN ).longValue();
			if(sign == 2 && vipDiscount.compareTo(new BigDecimal(1)) == -1) {
				ConsumptionNote notes = new ConsumptionNote();
				notes.setPassId(id);
				notes.setType(Const.CON_NOTE_VIP_DISCOUNT_TYPE);
				notes.setFreeCharge(Const.PUBLIC_NO);
				notes.setMoney(new BigDecimal(0).subtract(money.subtract(new BigDecimal(sumMoney))));
				notes.setTime(new Date());
				notes.setRemark("会员折扣："+Const.DISCOUNT.get(vipDiscount.toString())+"折");
				notes.setBack1(null != vip ? vip.getMobile1()+vip.getMobile2() : null);
				consumNoteService.consumption(notes);
			}
		}
		return sumMoney;
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
			consumptionNote.setBack3(boxtype+"");
			PassengerFlowNote note = passengerFlowNoteMapper.selectByPrimaryKey(consumptionNote.getPassId());
			note.setUseBox(1);
			passengerFlowNoteMapper.updateByPrimaryKeySelective(note);
		}else if(consumptionNote.getType() == Const.CON_NOTE_MAID_TYPE) {
			consumptionNote.setMoney(new BigDecimal(consumptionNote.getBack3()).multiply(new BigDecimal(consumptionNote.getBack2())).multiply(Const.MAID_FEE));
			consumptionNote.setRemark("女仆："+Const.MAID_FEE+"元/小时，"+consumptionNote.getBack2()+"位，"+consumptionNote.getBack3()+"小时，共计"+consumptionNote.getMoney().stripTrailingZeros().toPlainString()+"元。"+consumptionNote.getRemark());
		}else{
			consumptionNote.setRemark((consumptionNote.getRemark()==null?"":consumptionNote.getRemark()+",")+"共计"+consumptionNote.getMoney().stripTrailingZeros().toPlainString()+"元");
		}
		return consumNoteService.consumption(consumptionNote);
	}
	
	public int updatePassengerFlowNote(PassengerFlowNote passengerFlowNote) {
		return passengerFlowNoteMapper.updateByPrimaryKeySelective(passengerFlowNote);
	}

	@Override
	public List<PassengerFlowNote> getPassFlowList() {
		return passengerFlowNoteMapper.getPassFlowList();
	}

	@Override
	public int birthdayBenefits(Integer id, BigDecimal money) {
		ConsumptionNote notes = new ConsumptionNote();
		notes.setPassId(id);
		notes.setType(Const.CON_NOTE_BIRTHDAY_BUY_TYPE);
		notes.setFreeCharge(Const.PUBLIC_NO);
		notes.setMoney(money);
		notes.setTime(new Date());
		if(money.compareTo(new BigDecimal(198)) == 0){
		    notes.setBack3("1");
		    notes.setRemark("购买生日福利（小包），消费："+money.stripTrailingZeros().toPlainString()+"元");
		}else if(money.compareTo(new BigDecimal(368)) == 0){
		    notes.setBack3("2");
		    notes.setRemark("购买生日福利（大包），消费："+money.stripTrailingZeros().toPlainString()+"元");
		}
		int i = consumNoteService.consumption(notes);
		BigDecimal sumBoxMoney = consumptionNoteMapper.selectSumMoneyByPassIdAndType(id,2,notes.getBack3());
		if(i > 0 && sumBoxMoney != null &&  sumBoxMoney.compareTo(new BigDecimal(0)) == 1) {
			ConsumptionNote note = new ConsumptionNote();
			note.setPassId(id);
			note.setType(Const.CON_NOTE_BIRTHDAY_TYPE);
			note.setFreeCharge(Const.PUBLIC_NO);
			note.setMoney(new BigDecimal(0).subtract(sumBoxMoney));
			note.setTime(new Date());
			note.setRemark("生日福利抵消包厢费，抵消："+sumBoxMoney.stripTrailingZeros().toPlainString()+"元");
			if(money.compareTo(new BigDecimal(198)) == 0){
			    notes.setBack3("1");
			}else if(money.compareTo(new BigDecimal(368)) == 0){
			    notes.setBack3("2");
			}
			consumNoteService.consumption(note);
		}
		BigDecimal ruchangMoney = consumptionNoteMapper.selectRuchangMoneyByPassIdAndType(id,1);
		if(i > 0 && ruchangMoney != null &&  ruchangMoney.compareTo(new BigDecimal(0)) == 1) {
			ConsumptionNote note = new ConsumptionNote();
			note.setPassId(id);
			note.setType(Const.CON_NOTE_BIRTHDAY_TYPE);
			note.setFreeCharge(Const.PUBLIC_NO);
			note.setMoney(new BigDecimal(0).subtract(ruchangMoney));
			note.setTime(new Date());
			note.setRemark("生日福利抵消入场费，抵消："+ruchangMoney.stripTrailingZeros().toPlainString()+"元");
			if(money.compareTo(new BigDecimal(198)) == 0){
				notes.setBack3("1");
			}else if(money.compareTo(new BigDecimal(368)) == 0){
				notes.setBack3("2");
			}
			consumNoteService.consumption(note);
		}
		return i;
	}

	@Override
	public int addRecommender(PassengerFlowNote pass) {
		return passengerFlowNoteMapper.updateByPrimaryKeySelective(pass);
	}
	
	
}
