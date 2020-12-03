package com.young.mapper;

import com.young.model.VipUseNote;
import com.young.model.VipUseNoteExample;
import java.util.List;

import org.apache.ibatis.annotations.Update;

public interface VipUseNoteMapper {
    long countByExample(VipUseNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VipUseNote record);

    int insertSelective(VipUseNote record);

    List<VipUseNote> selectByExample(VipUseNoteExample example);

    VipUseNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipUseNote record);

    int updateByPrimaryKey(VipUseNote record);

    @Update("update vip_use_note set back1 = #{newName} where back1 = #{oldName} ")
	int updateVipNoteNameByName(String oldName, String newName);
}