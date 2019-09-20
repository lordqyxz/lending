package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Poundage;



public interface PoundageDao {
	List<Poundage> selectpa(Map<String, Object> map);
	int poundcount(Map<String, Object> map);
	int insert(Poundage po);
}
