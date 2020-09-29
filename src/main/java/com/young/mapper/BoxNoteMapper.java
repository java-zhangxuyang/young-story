package com.young.mapper;

import com.young.model.BoxNote;
import com.young.model.BoxNoteExample;
import java.util.List;

public interface BoxNoteMapper {
    long countByExample(BoxNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BoxNote record);

    int insertSelective(BoxNote record);

    List<BoxNote> selectByExample(BoxNoteExample example);

    BoxNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BoxNote record);

    int updateByPrimaryKey(BoxNote record);
}