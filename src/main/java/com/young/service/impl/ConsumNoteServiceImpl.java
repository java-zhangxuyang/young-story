package com.young.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.young.mapper.ConsumptionNoteMapper;
import com.young.model.ConsumptionNote;
import com.young.service.ConsumNoteService;

@Service("consumNoteServiceImpl")
@Transactional
public class ConsumNoteServiceImpl implements ConsumNoteService{

	@Autowired
	private ConsumptionNoteMapper consumptionNoteMapper;

	@Override
	public int consumption(ConsumptionNote note) {
		return consumptionNoteMapper.insertSelective(note);
	}

	@Override
	public List<ConsumptionNote> selectConsumptionNoteByPassId(Integer id) {
		return consumptionNoteMapper.selectConsumptionNoteByPassId(id);
	}
	
	
}
