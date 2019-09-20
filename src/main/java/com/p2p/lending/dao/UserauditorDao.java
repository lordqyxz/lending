package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Userauditor;

public interface UserauditorDao {
	
	public  List<Userauditor> queryUserauditor(Map<String, Object> map);
	
	public int addUserauditor(Map<String, Object> map);

}
