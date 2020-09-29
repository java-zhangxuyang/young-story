package com.young.mapper;

import com.young.model.BoxSubscribeNote;
import com.young.model.BoxSubscribeNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoxSubscribeNoteMapper {
    long countByExample(BoxSubscribeNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoxSubscribeNote record);

    int insertSelective(BoxSubscribeNote record);

    List<BoxSubscribeNote> selectByExample(BoxSubscribeNoteExample example);

    BoxSubscribeNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoxSubscribeNote record);

    int updateByPrimaryKey(BoxSubscribeNote record);
}