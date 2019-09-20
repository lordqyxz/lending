package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Biao;
import com.p2p.lending.entity.Borrowmoney;

import com.github.pagehelper.PageInfo;

public interface BiaoService {

	public List<Biao> findList(Map<String, Object> map);

	int delete(Integer id);

	int create(Biao biao);

	Biao get(Integer id);

	int update(Biao biao);
}
