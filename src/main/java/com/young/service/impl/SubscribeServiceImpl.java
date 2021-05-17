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
import com.young.mapper.BoxMapper;
import com.young.mapper.BoxSubscribeNoteMapper;
import com.young.model.Box;
import com.young.model.BoxSubscribeNote;
import com.young.service.SubscribeService;

@Service("subscribeServiceImpl")
@Transactional
public class SubscribeServiceImpl implements SubscribeService{

	@Autowired
	private BoxSubscribeNoteMapper boxSubscribeNoteMapper;
	@Autowired
	private BoxMapper boxMapper;
	

	public PageInfo<BoxSubscribeNote> getBoxSubscribeNoteList(Integer pageNum) {
		pageNum = PublicUtils.page(pageNum, false);
		PageHelper.startPage(pageNum, 10);
		List<BoxSubscribeNote> noteList = boxSubscribeNoteMapper.selectNoteList();
		PageInfo<BoxSubscribeNote> page = new PageInfo<>(noteList);
		return page;
	}

	@Override
	public int updateSubscribeStatus(Integer id, Integer status) {
		BoxSubscribeNote note = boxSubscribeNoteMapper.selectByPrimaryKey(id);
		note.setStatus(status);
		int i = boxSubscribeNoteMapper.updateByPrimaryKeySelective(note);
		if(i > 0) {
			if(status == 4) {
				Box box = boxMapper.selectByPrimaryKey(note.getBoxId());
				Date maxdate = boxSubscribeNoteMapper.selectMaxDateTodayNoId(box.getId(),id);
				Date now = new Date();
				if(maxdate != null && maxdate.getTime() > now.getTime()) {
					box.setStatus(Const.BOX_MAKE_STATUS);
				}else {
					box.setStatus(Const.BOX_NO_USE_STATUS);
				}
				box.setStatus(0);
				return boxMapper.updateByPrimaryKeySelective(box);
			}
			return i;
		}else {
			return 0;
		}
	}

}
