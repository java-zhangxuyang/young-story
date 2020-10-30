package com.young.mapper;

import com.young.model.Vip;
import com.young.model.VipExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VipMapper {
    long countByExample(VipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Vip record);

    int insertSelective(Vip record);

    List<Vip> selectByExample(VipExample example);

    Vip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);
    
    @Select("select vip.*,dict.name as levelName,concat(vip.mobile1,vip.mobile2) as mobile from vip LEFT JOIN dict on vip.`level` = dict.`no` where dict.`key`='vip_level' ORDER BY id")
    List<Vip> selectVipList();
}