package com.young.mapper;

import com.young.model.CheckWork;
import com.young.model.CheckWorkExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckWorkMapper {
    long countByExample(CheckWorkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckWork record);

    int insertSelective(CheckWork record);

    List<CheckWork> selectByExample(CheckWorkExample example);

    CheckWork selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckWork record);

    int updateByPrimaryKey(CheckWork record);
}