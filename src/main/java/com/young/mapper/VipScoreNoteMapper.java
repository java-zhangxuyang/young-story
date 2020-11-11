package com.young.mapper;

import com.young.model.VipScoreNote;
import com.young.model.VipScoreNoteExample;
import java.util.List;

public interface VipScoreNoteMapper {
    long countByExample(VipScoreNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VipScoreNote record);

    int insertSelective(VipScoreNote record);

    List<VipScoreNote> selectByExample(VipScoreNoteExample example);

    VipScoreNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipScoreNote record);

    int updateByPrimaryKey(VipScoreNote record);
}