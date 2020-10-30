package com.young.mapper;

import com.young.model.StaffNote;
import com.young.model.StaffNoteExample;
import java.util.List;

public interface StaffNoteMapper {
    long countByExample(StaffNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffNote record);

    int insertSelective(StaffNote record);

    List<StaffNote> selectByExample(StaffNoteExample example);

    StaffNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StaffNote record);

    int updateByPrimaryKey(StaffNote record);
}