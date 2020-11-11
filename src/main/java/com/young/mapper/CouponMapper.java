package com.young.mapper;

import com.young.model.Coupon;
import com.young.model.CouponExample;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    @Select("select c.*, d.name as typeName from coupon c left join dict d on c.type = d.no where d.`key` = 'coupon_type' order by c.id ")
	List<Coupon> getCouponList();
}