package com.young.mapper;

import com.young.model.PassengerFlowNote;
import com.young.model.PassengerFlowNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassengerFlowNoteMapper {
    long countByExample(PassengerFlowNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PassengerFlowNote record);

    int insertSelective(PassengerFlowNote record);

    List<PassengerFlowNote> selectByExample(PassengerFlowNoteExample example);

    PassengerFlowNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PassengerFlowNote record);

    int updateByPrimaryKey(PassengerFlowNote record);
}