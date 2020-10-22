package com.young.service;

import java.util.List;
import java.util.Map;

import com.young.base.support.ResponseBo;
import com.young.model.Box;
import com.young.model.BoxNote;
import com.young.model.BoxSubscribeNote;

public interface BoxService {

	List<Box> getBoxList();

	ResponseBo useBox(BoxNote boxNote);

	int updateBoxName(Box boxe);

	int continuation(Box box);

	int departureBox(Box box);

	int makeBox(BoxSubscribeNote boxSubscribeNote);

	int updaetMakeBox(BoxSubscribeNote boxSubscribeNote);

	Map<String, List<BoxSubscribeNote>> selectSubscribeToday();
}
