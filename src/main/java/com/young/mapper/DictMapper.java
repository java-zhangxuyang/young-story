package com.young.mapper;

import com.young.model.Dict;
import com.young.model.DictExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictMapper {
    long countByExample(DictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}