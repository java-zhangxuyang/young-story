package com.young.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.constant.Const;
import com.young.base.utils.PublicUtils;
import com.young.mapper.VipMapper;
import com.young.mapper.VipUseNoteMapper;
import com.young.model.Staff;
import com.young.model.Vip;
import com.young.model.VipUseNote;
import com.young.service.VipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("vipServiceImpl")
@Transactional
public class VipServiceImpl implements VipService{

	@Autowired
	private VipMapper vipMapper;
	@Autowired
	private VipUseNoteMapper vipUseNoteMapper;

	@Override
	public PageInfo<Vip> getVipList(Integer pageNum) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<Vip> vipList = vipMapper.selectVipList();
		PageInfo<Vip> page = new PageInfo<>();
		page.setList(vipList);
		return page;
	}

	@Override
	public int addVip(Vip vip) {
		vip.setMobile1(vip.getMobile().substring(0, 6));
		vip.setMobile2(vip.getMobile().substring(6, 11));
		vip.setLevel(1);
		vip.setNowMoney(new BigDecimal(0));
		vip.setTotalMoney(new BigDecimal(0));
		return vipMapper.insertSelective(vip);
	}

	@Override
	public int vipRecharge(Vip vip) {
		Vip v = vipMapper.selectByPrimaryKey(vip.getId());
		VipUseNote note = new VipUseNote();
		note.setVipId(v.getId());
		note.setVipName(v.getName());
		note.setType(Const.VIP_USE_NOTE_TYPE_VIP_RECHARGE);
		note.setTime(new Date());
		note.setMoney(vip.getMoney());
		int i = vipUseNoteMapper.insertSelective(note);
		if(i > 0) {
			v.setNowMoney(v.getNowMoney().add(vip.getMoney()));
			v.setTotalMoney(v.getTotalMoney().add(vip.getMoney()));
			//TODO  会员等级  会员机会
			return vipMapper.updateByPrimaryKeySelective(v);
		}
		return 0;
	}
	
	

	
}
