package com.young.service;


import com.github.pagehelper.PageInfo;
import com.young.model.Activity;

public interface ActivityService {

	PageInfo<Activity> getActivityList(Integer pageNum);

	int updateActivityStatus(Integer id);

	int addActivity(Activity activity, String startTime, String endTime);

	int deleteActivity(Integer id);

	
}
