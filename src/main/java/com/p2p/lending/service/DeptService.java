package com.p2p.lending.service;

import java.util.List;

import com.p2p.lending.pojo.Dept;

public interface DeptService {
	List<Dept> findall();

	Dept findbyid(Integer did);

	int insert(Dept dept);

	int del(Integer did);

	int upd(Dept dept);
}
