package com.p2p.lending.dao;

import java.util.List;

import com.p2p.lending.entity.Dept;

public interface DeptDao {

	List<Dept> findall();

	Dept findbyid(Integer did);

	int insert(Dept dept);

	int del(Integer did);

	int upd(Dept dept);
}
