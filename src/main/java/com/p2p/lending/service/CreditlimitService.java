package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.pojo.Creditlimit;

public interface CreditlimitService {

	public List<Creditlimit> queryCreditlimits(Map<String, Object> map);
	public int updateCreditlimit(Map<String, Object> map);
	public int insertCreditlimit(Map<String, Object> map);

}
