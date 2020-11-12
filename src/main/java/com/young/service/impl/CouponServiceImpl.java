package com.young.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.constant.Const;
import com.young.base.utils.PublicUtils;
import com.young.mapper.ActivityMapper;
import com.young.mapper.BoxSubscribeNoteMapper;
import com.young.mapper.CouponMapper;
import com.young.model.Activity;
import com.young.model.ActivityExample;
import com.young.model.ActivityExample.Criteria;
import com.young.model.BoxSubscribeNote;
import com.young.model.Coupon;
import com.young.service.ActivityService;
import com.young.service.CouponService;
import com.young.service.SubscribeService;

import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("couponServiceImpl")
@Transactional
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponMapper couponMapper;

	@Override
	public PageInfo<Coupon> getCouponList(Integer pageNum) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<Coupon> couponList = couponMapper.getCouponList();
		PageInfo<Coupon> page = new PageInfo<>(couponList);
		return page;
	}


	@Override
	public int addCoupon(Coupon coupon) {
		return couponMapper.insertSelective(coupon);
	}

	@Override
	public int deleteCoupon(Integer id) {
		return couponMapper.deleteByPrimaryKey(id);
	}


	@Override
	public List<Coupon> getCouponList() {
		return couponMapper.getCouponList();
	};

}
