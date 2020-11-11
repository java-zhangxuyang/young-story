package com.young.mapper;

import com.young.model.Activity;
import com.young.model.ActivityExample;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    @Select("select * from activity where status = 1 and start_time < now() and end_time > now() and type = #{type} order by start_time desc ")
    List<Activity> selectActivityNowListByType(Integer type);
}