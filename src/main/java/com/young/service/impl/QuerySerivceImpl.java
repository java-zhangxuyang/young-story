package com.young.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.utils.NumberUtils;
import com.young.base.utils.PublicUtils;
import com.young.mapper.ConsumptionNoteMapper;
import com.young.mapper.PassengerFlowNoteMapper;
import com.young.mapper.VipMapper;
import com.young.mapper.VipUseNoteMapper;
import com.young.model.PassengerFlowNote;
import com.young.model.Vip;
import com.young.service.QueryService;

import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("querySerivceImpl")
public class QuerySerivceImpl implements QueryService {
	
	@Autowired
	private PassengerFlowNoteMapper passengerFlowNoteMapper;
	@Autowired
	private ConsumptionNoteMapper consumptionNoteMapper;
	@Autowired
	private VipUseNoteMapper vipUseNoteMapper;
	@Autowired
	private VipMapper vipMapper;
	
	@Override
	public PageInfo<PassengerFlowNote> passengerQuery(Integer pageNum,String startTime, String endTime) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<PassengerFlowNote> list = passengerFlowNoteMapper.passengerQuery(startTime, endTime);
		PageInfo<PassengerFlowNote> page = new PageInfo<>(list);
		return page;
	}

	@Override
	public Map<String, Object> getTableData(String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.getPassengerData(map,date);
		map = this.getConsumptionData(map,date);
		map = this.getVipData(map,date);
		map = this.getChartData(map,date);
		map = this.getVipTableData(map);
		return map;
	}

	private Map<String, Object> getVipTableData(Map<String, Object> map) {
		List<Map<String, Object>> list = vipMapper.getVipTableData();
		map.put("vipList", list);
		Map<String, Object> vipSumMap = vipMapper.getVipTableSumData();
		map.put("vipSumMap", vipSumMap);
		return map;
	}

	private Map<String, Object> getChartData(Map<String, Object> map, String date) {
		Integer day = 0;
		JDateTime jTime = new JDateTime();
		if(jTime.toString("YYYY-MM").equals(date)) {
			day = jTime.getDay();
		}else {
			jTime = new JDateTime(date);
			day = this.getDaysOfMonth(jTime.convertToDate());
		}
		NumberUtils numberUtils = new NumberUtils();
		map.put("month", numberUtils.cvt(jTime.getMonth(),true));
		map.put("day", day);
		List<Integer> yye = new ArrayList<Integer>();
		List<Integer> people = new ArrayList<Integer>();
		List<Integer> batch = new ArrayList<Integer>();
		List<Integer> box = new ArrayList<Integer>();
		List<Integer> girl = new ArrayList<Integer>();
		List<Map<String, Object>> data1 = passengerFlowNoteMapper.getChartData(date,day);
		for (Map<String, Object> mapdata : data1) {
			Double money =Double.parseDouble(mapdata.get("money").toString());
			yye.add(money.intValue());
			people.add(Integer.parseInt(mapdata.get("people").toString()));
			batch.add(Integer.parseInt(mapdata.get("batch").toString()));
		}
		map.put("yye", yye);
		map.put("people", people);
		map.put("batchs", batch);
		List<Map<String, Object>> boxdata = consumptionNoteMapper.getChartBoxData(date,day);
		for (Map<String, Object> mapbox : boxdata) {
			box.add(Integer.parseInt(mapbox.get("box").toString()));
		}
		map.put("box", box);
		List<Map<String, Object>> girldata = consumptionNoteMapper.getChartGirlData(date,day);
		for (Map<String, Object> mapgirl : girldata) {
			girl.add(Integer.parseInt(mapgirl.get("girl").toString()));
		}
		map.put("girl", girl);
		return map;
	}
	
	public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

	private Map<String, Object> getVipData(Map<String, Object> map, String date) {
		map.putAll(vipUseNoteMapper.getvipTableSumData(date));
		return map;
	}

	private Map<String, Object> getConsumptionData(Map<String, Object> map, String date) {
		map.putAll(consumptionNoteMapper.getBoxData(date));
		map.putAll(consumptionNoteMapper.getgirlData(date));
		return map;
	}

	private Map<String, Object> getPassengerData(Map<String, Object> map, String date) {
		//营业总额：客流批次：次均消费：客流人数：人均消费：
		map.putAll(passengerFlowNoteMapper.getTableData1(date));
		//普通消费：
		map.putAll(passengerFlowNoteMapper.getTableDataPt(date));
		//会员消费：
		map.putAll(passengerFlowNoteMapper.getTableDataVip(date));
		return map;
	}
	
	

}
