package com.young.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.utils.PublicUtils;
import com.young.mapper.CouponMapper;
import com.young.model.Coupon;
import com.young.service.CouponService;

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
	public List<Coupon> getCoupondkqList(Integer type) {
		return couponMapper.getCoupondkqList(type);
	};

}
