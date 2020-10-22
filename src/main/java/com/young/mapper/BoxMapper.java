package com.young.mapper;

import com.young.model.Box;
import com.young.model.BoxExample;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface BoxMapper {
    long countByExample(BoxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Box record);

    int insertSelective(Box record);

    List<Box> selectByExample(BoxExample example);

    Box selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Box record);

    int updateByPrimaryKey(Box record);

    @Select("select * from box where back2 = ${number} ")
	Box selectBoxByNumber(Integer number);
}