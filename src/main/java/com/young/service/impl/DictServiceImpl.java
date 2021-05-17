package com.young.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.mapper.DictMapper;
import com.young.model.Dict;
import com.young.service.DictService;

@Service("dictServiceImpl")
public class DictServiceImpl implements DictService{

	@Autowired
	private DictMapper dictMapper;
	
	//根据key获取字典列表
	public List<Dict> getDictList(String key){
		List<Dict> list = dictMapper.getDictList(key);
		return list;
	}
	
}
