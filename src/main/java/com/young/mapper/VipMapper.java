package com.young.mapper;

import com.young.model.Vip;
import com.young.model.VipExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
}