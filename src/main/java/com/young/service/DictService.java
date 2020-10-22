package com.young.service;

import java.util.List;

import com.young.model.Dict;

public interface DictService {

	//根据key获取字典列表
	List<Dict> getDictList(String key);

}
