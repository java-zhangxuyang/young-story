package com.young.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.base.utils.PublicUtils;
import com.young.mapper.BoxSubscribeNoteMapper;
import com.young.model.BoxSubscribeNote;
import com.young.service.SubscribeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("subscribeServiceImpl")
@Transactional
public class SubscribeServiceImpl implements SubscribeService{

	@Autowired
	private BoxSubscribeNoteMapper boxSubscribeNoteMapper;

	public PageInfo<BoxSubscribeNote> getBoxSubscribeNoteList(Integer pageNum) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<BoxSubscribeNote> noteList = boxSubscribeNoteMapper.selectNoteList();
		PageInfo<BoxSubscribeNote> page = new PageInfo<>();
		page.setList(noteList);
		return page;
	}

	@Override
	public int updateSubscribeStatus(Integer id, Integer status) {
		BoxSubscribeNote note = new BoxSubscribeNote();
		note.setId(id);
		note.setStatus(status);
		return boxSubscribeNoteMapper.updateByPrimaryKeySelective(note);
	}

}
