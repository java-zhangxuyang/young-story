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
import com.young.base.support.ResponseBo;
import com.young.base.utils.PublicUtils;
import com.young.mapper.FlushingMapper;
import com.young.mapper.VipMapper;
import com.young.mapper.VipUseNoteMapper;
import com.young.model.Flushing;
import com.young.model.FlushingExample;
import com.young.model.Vip;
import com.young.model.VipUseNote;
import com.young.service.VipService;

import jodd.util.StringUtil;

@Service("vipServiceImpl")
@Transactional
public class VipServiceImpl implements VipService{

	@Autowired
	private VipMapper vipMapper;
	@Autowired
	private VipUseNoteMapper vipUseNoteMapper;
	@Autowired
	private FlushingMapper flushingMapper;

	@Override
	public PageInfo<Vip> getVipList(Integer pageNum, String mobile, String vipName) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		String sql = "";
		if(StringUtil.isNotBlank(mobile)) {
			sql += " and vip.mobile2 like '%"+mobile+"' ";
		}
		if(StringUtil.isNotBlank(vipName)) {
			sql += " and vip.name like '%"+vipName+"%' ";
		}
		List<Vip> vipList = vipMapper.selectVipList(sql);
		PageInfo<Vip> page = new PageInfo<>(vipList);
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
		List<Flushing> list = flushingMapper.selectFlushByRecharge(vip.getMoney());
		if(null != list && list.size() > 0) {
			Flushing flush = list.get(0);
			Vip v = vipMapper.selectByPrimaryKey(vip.getId());
			VipUseNote note = new VipUseNote();
			note.setVipId(v.getId());
			note.setVipName(v.getName());
			note.setType(Const.VIP_USE_NOTE_TYPE_VIP_RECHARGE);
			note.setTime(new Date());
			note.setMoney(flush.getRecharge());
			note.setRemark(flush.getName());
			note.setBack1(vip.getBack1());
			int i = vipUseNoteMapper.insertSelective(note);
			if(i > 0) {
				//会员等级
				v = this.vipLevelRecharge(flush.getRecharge(), v);
				v.setNowMoney(v.getNowMoney().add(flush.getTotal()));
				v.setTotalMoney(v.getTotalMoney().add(flush.getRecharge()));
				return vipMapper.updateByPrimaryKeySelective(v);
			}
		}
		return 0;
	}

	@Override
	public List<Flushing> getFlushingList() {
		FlushingExample example = new FlushingExample();
		example.setOrderByClause(" id asc");
		return flushingMapper.selectByExample(example);
	}

	@Override
	public Vip getVipByMobile(String mobile) {
		return vipMapper.selectVipByMobile(mobile);
	}
	
	
	public Vip vipLevelRecharge(BigDecimal money,Vip vip) {
		Double dmoney = money.doubleValue();
		if(1000d <= dmoney && dmoney < 3000d && vip.getLevel() < 2) {
			vip.setLevel(2);
		}else if(3000d <= dmoney && dmoney < 5000d && vip.getLevel() < 3) {
			vip.setLevel(3);
		}else if(5000d <= dmoney && dmoney < 10000d && vip.getLevel() < 4) {
			vip.setLevel(4);
		}else if(10000d <= dmoney && vip.getLevel() < 5) {
			vip.setLevel(5);
		}
		return vip;
	}

	@Override
	public ResponseBo vipLevelUpgrade(Integer id) {
		Vip vip = vipMapper.selectByPrimaryKey(id);
		BigDecimal needScore = null;
		if(vip.getLevel() == 1) {
			needScore = Const.LEVELUPGRADE.get("1-2");
		}else if(vip.getLevel() == 2) {
			needScore = Const.LEVELUPGRADE.get("2-3");
		}else if(vip.getLevel() == 3) {
			needScore = Const.LEVELUPGRADE.get("3-4");
		}
		if(vip.getScore().compareTo(needScore) == -1) {
			return ResponseBo.fail("所需积分不足！");
		}else {
			vip.setLevel(vip.getLevel() + 1);
			vip.setScore(vip.getScore().subtract(needScore));
			int i =vipMapper.updateByPrimaryKeySelective(vip);
			return ResponseBo.ok(i);
		}
	}

	
}
