package com.p2p.lending.impl;

import java.util.List;
import java.util.Map;

import com.p2p.lending.dao.UserauditorDao;
import com.p2p.lending.pojo.Userauditor;
import com.p2p.lending.service.UserauditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserauditorServiceImpl implements UserauditorService{
	
	@Autowired
	private UserauditorDao  userauditorDao;
	
	@Override
	public List<Userauditor> queryUseraubitor(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userauditorDao.queryUserauditor(map);
	}

	@Override
	public int addUserauditor(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userauditorDao.addUserauditor(map);
	}

	
}
