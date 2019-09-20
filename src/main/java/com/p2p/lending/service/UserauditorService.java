package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Userauditor;

public interface UserauditorService {
		
	public List<Userauditor> queryUseraubitor(Map<String, Object> map);
	
	public int addUserauditor(Map<String, Object> map);
}
