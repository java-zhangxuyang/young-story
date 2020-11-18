package com.young.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.young.base.support.ResponseBo;
import com.young.model.Flushing;
import com.young.model.Staff;
import com.young.model.Vip;

public interface VipService {

	PageInfo<Vip> getVipList(Integer pageNum, String mobile, String vipName);

	int addVip(Vip vip);

	int vipRecharge(Vip vip);

	List<Flushing> getFlushingList();
	
	Vip getVipByMobile(String mobile);

	ResponseBo vipLevelUpgrade(Integer id);

}
