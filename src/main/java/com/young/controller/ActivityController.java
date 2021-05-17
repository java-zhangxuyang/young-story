package com.young.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.young.base.support.ResponseBo;
import com.young.model.Activity;
import com.young.service.ActivityService;

@Controller
@RequestMapping("/admin/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	/*
	 * 活动列表界面
	 */
	@GetMapping("")
	public String activity(Integer pageNum, Model model,HttpServletRequest request) {
		PageInfo<Activity> activityList = activityService.getActivityList(pageNum);
		model.addAttribute("activitys", activityList);
		return "admin_activity";
	}
	
	/*
	 * 修改活动状态
	 */
	@PostMapping("/updateActivityStatus")
	@ResponseBody
	public Object updateActivityStatus(Integer id) {
		int i = activityService.updateActivityStatus(id);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	
	/*
	 * 添加活动
	 */
	@PostMapping("/addActivity")
	@ResponseBody
	public Object addActivity(Activity activity,String startTimes,String endTimes) {
		int i = activityService.addActivity(activity,startTimes,endTimes);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	/*
	 * 删除活动
	 */
	@PostMapping("/deleteActivity")
	@ResponseBody
	public Object deleteActivity(Integer id) {
		int i = activityService.deleteActivity(id);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	
}
