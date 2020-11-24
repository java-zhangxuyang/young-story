package com.young.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.young.model.Coupon;

public interface CouponService {

	int deleteCoupon(Integer id);

	int addCoupon(Coupon coupon);

	PageInfo<Coupon> getCouponList(Integer pageNum);
	
	List<Coupon> getCoupondkqList(Integer type);

	
}
