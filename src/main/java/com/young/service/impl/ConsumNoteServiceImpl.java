package com.young.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.mapper.BoxMapper;
import com.young.mapper.ConsumptionNoteMapper;
import com.young.mapper.DictMapper;
import com.young.model.Box;
import com.young.model.BoxExample;
import com.young.model.ConsumptionNote;
import com.young.model.ConsumptionNoteExample;
import com.young.model.Dict;
import com.young.service.BoxService;
import com.young.service.ConsumNoteService;
import com.young.service.DictService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("consumNoteServiceImpl")
public class ConsumNoteServiceImpl implements ConsumNoteService{

	@Autowired
	private ConsumptionNoteMapper consumptionNoteMapper;

	@Override
	public int consumption(ConsumptionNote note) {
		return consumptionNoteMapper.insertSelective(note);
	}

	@Override
	public List<ConsumptionNote> selectConsumptionNoteById(Integer id) {
		return consumptionNoteMapper.selectConsumptionNoteById(id);
	}
	
	
}
