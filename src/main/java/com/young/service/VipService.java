package com.young.service;


import com.github.pagehelper.PageInfo;
import com.young.model.Staff;
import com.young.model.Vip;

public interface VipService {

	PageInfo<Vip> getVipList(Integer pageNum);

	int addVip(Vip vip);

	int vipRecharge(Vip vip);

}
