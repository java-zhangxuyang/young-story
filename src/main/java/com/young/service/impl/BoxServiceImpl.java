package com.young.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.base.constant.Const;
import com.young.base.support.ResponseBo;
import com.young.mapper.BoxMapper;
import com.young.mapper.BoxNoteMapper;
import com.young.mapper.BoxSubscribeNoteMapper;
import com.young.mapper.PassengerFlowNoteMapper;
import com.young.model.Box;
import com.young.model.BoxExample;
import com.young.model.BoxNote;
import com.young.model.BoxSubscribeNote;
import com.young.model.ConsumptionNote;
import com.young.model.PassengerFlowNote;
import com.young.service.BoxService;
import com.young.service.ConsumNoteService;

import jodd.datetime.JDateTime;

@Service("boxServiceImpl")
@Transactional
public class BoxServiceImpl implements BoxService{

	@Autowired
	private BoxMapper boxMapper;
	@Autowired
	private BoxNoteMapper boxNoteMapper;
	@Autowired
	private BoxSubscribeNoteMapper boxSubscribeNoteMapper;
	@Autowired
	private PassengerFlowNoteMapper passengerFlowNoteMapper;
	@Autowired
	private ConsumNoteService consumNoteService;
	
	//获取包厢列表
	public List<Box> getBoxList(){
		BoxExample example = new BoxExample();
		example.setOrderByClause(" id ");
		List<Box> list = boxMapper.selectByExample(example);
		return list;
	}
	
	//获取包厢预订集合
	public Map<String, List<BoxSubscribeNote>> selectSubscribeToday(){
		Map<String, List<BoxSubscribeNote>> map = new HashMap<String, List<BoxSubscribeNote>>();
		List<BoxSubscribeNote> notelist = boxSubscribeNoteMapper.selectSubscribeToday();
		if(null != notelist && notelist.size() > 0) {
			for (BoxSubscribeNote boxSubscribeNote : notelist) {
				if(map.containsKey(boxSubscribeNote.getBoxId().toString())) {
					map.get(boxSubscribeNote.getBoxId().toString()).add(boxSubscribeNote);
				}else {
					List<BoxSubscribeNote> list = new ArrayList<BoxSubscribeNote>();
					list.add(boxSubscribeNote);
					map.put(boxSubscribeNote.getBoxId().toString(), list);
				}
			}
		}
		return map;
	}

	//使用包厢
	@Override
	public ResponseBo useBox(BoxNote boxNote) {
		Box box = boxMapper.selectByPrimaryKey(boxNote.getBoxId());
		if(Const.BOX_USE_STATUS.equals(box.getStatus())) {
			return ResponseBo.fail("该包厢正被使用中！");
		}
		Box usebox = boxMapper.selectBoxByNumber(boxNote.getNumber());
		if(null != usebox) {
			return ResponseBo.fail("该号码牌已被使用！");
		}
		PassengerFlowNote passFlow = passengerFlowNoteMapper.selectTodayByNumber(boxNote.getNumber());
		if(null == passFlow) {
			return ResponseBo.fail("该号码牌不存在！");
		}
		JDateTime now = new JDateTime().subSecond(5);
		boxNote.setStartTime(now.convertToDate());
		Date endTime = new JDateTime().addMinute((int)(60*boxNote.getUseDate())).convertToDate();
		boxNote.setEndTime(endTime);
		int noteid = boxNoteMapper.insert(boxNote);
		if(noteid > 0) {
			box.setStatus(Const.BOX_USE_STATUS);
			box.setUseDuration(boxNote.getUseDate());
			box.setAdmissionTime(now.convertToDate());
			box.setToTime(endTime);
			box.setRemind(boxNote.getRemind());
			box.setBack2(boxNote.getNumber()+"");
			passFlow.setUseBox(Const.PUBLIC_YES);
			passFlow.setUseTime(now.convertToDate());
			passengerFlowNoteMapper.updateByPrimaryKeySelective(passFlow);
			if(null != box.getRemind() && box.getRemind() == 1) {
				ConsumptionNote notes = new ConsumptionNote();
				notes.setPassId(passFlow.getId());
				notes.setType(Const.CON_NOTE_BOX_TYPE);
				notes.setFreeCharge(Const.PUBLIC_NO);
				notes.setMoney(box.getPrice().multiply(new BigDecimal(box.getUseDuration())));
				notes.setTime(now.convertToDate());
				notes.setRemark("包厢："+box.getPrice().stripTrailingZeros().toPlainString()+"元/小时，"+new BigDecimal(box.getUseDuration()).stripTrailingZeros().toPlainString()+"小时，共计"+notes.getMoney().stripTrailingZeros().toPlainString()+"元");
				notes.setBack3(box.getType()+"");
				consumNoteService.consumption(notes);
			}
			int j = boxMapper.updateByPrimaryKeySelective(box);
			
			if(j > 0) {
				return ResponseBo.ok();
			}else {
				return ResponseBo.fail();
			}
		}else {
			return ResponseBo.fail();
		}
	}
	
	//修改包厢名字
	@Override
	public int updateBoxName(Box box) {
		Box oldbox = boxMapper.selectByPrimaryKey(box.getId());
		oldbox.setName(box.getName());
		return boxMapper.updateByPrimaryKeySelective(oldbox);
	}

	//续时
	@Override
	public int continuation(Box box) {
		Box oldbox = boxMapper.selectByPrimaryKey(box.getId());
		JDateTime now = new JDateTime(oldbox.getToTime());
		oldbox.setUseDuration(oldbox.getUseDuration() + box.getUseDuration());
		oldbox.setToTime(now.addMinute((int)(60*box.getUseDuration())).convertToDate());
		int i = boxMapper.updateByPrimaryKeySelective(oldbox);
		if(i > 0) {
			PassengerFlowNote passFlow = passengerFlowNoteMapper.selectTodayByNumber(oldbox.getBack2());
			ConsumptionNote notes = new ConsumptionNote();
			notes.setPassId(passFlow.getId());
			notes.setType(Const.CON_NOTE_BOX_TYPE);
			notes.setFreeCharge(Const.PUBLIC_NO);
			BigDecimal useDuration = new BigDecimal(box.getUseDuration());
			notes.setMoney(oldbox.getPrice().multiply(useDuration));
			notes.setTime(new Date());
			notes.setRemark("包厢续费："+oldbox.getPrice().stripTrailingZeros().toPlainString()+"元/小时，"+useDuration.stripTrailingZeros().toPlainString()+"小时，共计"+notes.getMoney().stripTrailingZeros().toPlainString()+"元");
			consumNoteService.consumption(notes);
			return Const.PUBLIC_YES;
		}else {
			return Const.PUBLIC_NO;
		}
	}

	//出包
	@Override
	public int departureBox(Box box) {
		Box oldbox = boxMapper.selectByPrimaryKey(box.getId());
		Date now = new Date();
		if(null != box.getRemind() && box.getRemind() == 0) {
			PassengerFlowNote passFlow = passengerFlowNoteMapper.selectTodayByNumber(oldbox.getBack2());
			ConsumptionNote notes = new ConsumptionNote();
			notes.setPassId(passFlow.getId());
			notes.setType(Const.CON_NOTE_BOX_TYPE);
			notes.setFreeCharge(Const.PUBLIC_NO);
			Double d = Math.ceil((now.getTime() - oldbox.getAdmissionTime().getTime())/1000/60/60);
			notes.setMoney(box.getPrice().multiply(new BigDecimal(d)));
			notes.setTime(now);
			notes.setRemark("包厢："+box.getPrice().stripTrailingZeros().toPlainString()+"元/小时，"+d+"小时，共计"+notes.getMoney().stripTrailingZeros().toPlainString()+"元");
			consumNoteService.consumption(notes);
		}
		Date maxdate = boxSubscribeNoteMapper.selectMaxDateToday(box.getId());
		if(maxdate != null && maxdate.getTime() > now.getTime()) {
			oldbox.setStatus(Const.BOX_MAKE_STATUS);
		}else {
			oldbox.setStatus(Const.BOX_NO_USE_STATUS);
		}
		oldbox.setUseDuration(null);
		oldbox.setAdmissionTime(null);
		oldbox.setToTime(null);
		oldbox.setRemind(0);
		oldbox.setBack1(null);
		oldbox.setBack2(null);
		return boxMapper.updateByPrimaryKey(oldbox);
	}
	
	//预约包厢
	@Override
	public int makeBox(BoxSubscribeNote boxSubscribeNote) {
		boxSubscribeNote.setStatus(1);
		int boxSubscribeNoteId = boxSubscribeNoteMapper.insertSelective(boxSubscribeNote);
		if(boxSubscribeNoteId > 0) {
			JDateTime data = new JDateTime().setHour(23).setMinute(59).setSecond(59).setMillisecond(999);
			if(boxSubscribeNote.getToTime().getTime() <= data.getTimeInMillis()) {
				Box box = boxMapper.selectByPrimaryKey(boxSubscribeNote.getBoxId());
				box.setStatus(Const.BOX_MAKE_STATUS);
				boxMapper.updateByPrimaryKeySelective(box);
			}
			return Const.PUBLIC_YES;
		}else {
			return Const.PUBLIC_NO;
		}
	}
	
	//更新预约包厢状态
	@Override
	public int updaetMakeBox(BoxSubscribeNote boxSubscribeNote) {
		boxSubscribeNote.setToTime(new Date());
		int boxSubscribeNoteId = boxSubscribeNoteMapper.insertSelective(boxSubscribeNote);
		if(boxSubscribeNoteId > 0) {
			Box oldbox = boxMapper.selectByPrimaryKey(boxSubscribeNote.getBoxId());
			oldbox.setStatus(Const.BOX_MAKE_STATUS);
			int i = boxMapper.updateByPrimaryKeySelective(oldbox);
			if(i > 0) {
				return Const.PUBLIC_YES;
			}else {
				return Const.PUBLIC_NO;
			}
		}else {
			return Const.PUBLIC_NO;
		}
	}
	
}
