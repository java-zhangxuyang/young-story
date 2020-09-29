package com.young.mapper;

import com.young.model.Box;
import com.young.model.BoxExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoxMapper {
    long countByExample(BoxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Box record);

    int insertSelective(Box record);

    List<Box> selectByExample(BoxExample example);

    Box selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Box record);

    int updateByPrimaryKey(Box record);
}