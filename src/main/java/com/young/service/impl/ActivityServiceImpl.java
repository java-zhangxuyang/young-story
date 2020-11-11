package com.young.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.constant.Const;
import com.young.base.utils.PublicUtils;
import com.young.mapper.ActivityMapper;
import com.young.mapper.BoxSubscribeNoteMapper;
import com.young.model.Activity;
import com.young.model.ActivityExample;
import com.young.model.ActivityExample.Criteria;
import com.young.model.BoxSubscribeNote;
import com.young.service.ActivityService;
import com.young.service.SubscribeService;

import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("activityServiceImpl")
@Transactional
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper activityMapper;

	@Override
	public PageInfo<Activity> getActivityList(Integer pageNum) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		ActivityExample example = new ActivityExample();
		example.setOrderByClause(" status desc, end_time asc");
		List<Activity> activityList = activityMapper.selectByExample(example);
		PageInfo<Activity> page = new PageInfo<>();
		page.setList(activityList);
		return page;
	}

	@Override
	public int updateActivityStatus(Integer id) {
		Activity activity = activityMapper.selectByPrimaryKey(id);
		if(activity.getStatus() == 0) {
			activity.setStatus(1);
		}else {
			activity.setStatus(0);
		}
		return activityMapper.updateByPrimaryKeySelective(activity);
	}

	@Override
	public int addActivity(Activity activity,String startTime,String endTime) {
		Date time = new JDateTime(endTime).setHour(23).setMinute(59).setSecond(59).convertToDate();
		activity.setEndTime(time);
		activity.setStartTime(new JDateTime(startTime).convertToDate());
		activity.setStatus(Const.PUBLIC_YES);
		return activityMapper.insertSelective(activity);
	}

	@Override
	public int deleteActivity(Integer id) {
		return activityMapper.deleteByPrimaryKey(id);
	};

}
