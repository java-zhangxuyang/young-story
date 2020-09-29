package com.young.mapper;

import com.young.model.VipCouponNote;
import com.young.model.VipCouponNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VipCouponNoteMapper {
    long countByExample(VipCouponNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VipCouponNote record);

    int insertSelective(VipCouponNote record);

    List<VipCouponNote> selectByExample(VipCouponNoteExample example);

    VipCouponNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipCouponNote record);

    int updateByPrimaryKey(VipCouponNote record);
}