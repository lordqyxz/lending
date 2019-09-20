package com.p2p.lending.service;

import java.util.Map;

import com.p2p.lending.entity.Certification;

public interface CertificationService {
	public String selectM(Integer uid);
	public boolean updateM(Map<String, Object> map);
	public boolean undate(Map<String, Object> map);
	public boolean upm(Map<String, Object> map); 
	public int insert(Certification cer);
	public Certification select(Integer uid);
}
