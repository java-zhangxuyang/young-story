package com.young.mapper;

import com.young.model.Flushing;
import com.young.model.FlushingExample;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface FlushingMapper {
    long countByExample(FlushingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Flushing record);

    int insertSelective(Flushing record);

    List<Flushing> selectByExample(FlushingExample example);

    Flushing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flushing record);

    int updateByPrimaryKey(Flushing record);
    
}