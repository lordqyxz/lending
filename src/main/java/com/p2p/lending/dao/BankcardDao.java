package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Bankcard;

public interface BankcardDao {
	List<Bankcard> selectbc(Map<String, Object> map);
	int bankcount(Map<String, Object> map);
}
