package com.young.service;


import com.github.pagehelper.PageInfo;
import com.young.model.BoxSubscribeNote;

public interface SubscribeService {

	PageInfo<BoxSubscribeNote> getBoxSubscribeNoteList(Integer pageNum);

	int updateSubscribeStatus(Integer id, Integer status);
}
