package com.young.mapper;

import com.young.model.Coupon;
import com.young.model.CouponExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}