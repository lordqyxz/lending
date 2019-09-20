package com.p2p.lending.impl;

import java.util.List;

import com.p2p.lending.dao.DeptDao;
import com.p2p.lending.entity.Dept;
import com.p2p.lending.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao deptdao;

	public List<Dept> findall() {
		return deptdao.findall();
	}

	public int insert(Dept dept) {
		return deptdao.insert(dept);
	}

	public int del(Integer did) {
		return deptdao.del(did);
	}

	public Dept findbyid(Integer did) {

		return deptdao.findbyid(did);
	}

	public int upd(Dept dept) {

		return deptdao.upd(dept);
	}

	
}
