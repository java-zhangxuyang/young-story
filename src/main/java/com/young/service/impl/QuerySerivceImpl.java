package com.young.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.young.service.QueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("querySerivceImpl")
public class QuerySerivceImpl implements QueryService {
	
	
	@Override
	public Map<String, Object> passengerQuery(String sign) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sign", sign);
		return map;
	}

	

}
