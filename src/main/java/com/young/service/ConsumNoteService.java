package com.young.service;


import java.util.List;

import com.young.model.ConsumptionNote;

public interface ConsumNoteService {

	int consumption(ConsumptionNote note);

	List<ConsumptionNote> selectConsumptionNoteByPassId(Integer id);

}
