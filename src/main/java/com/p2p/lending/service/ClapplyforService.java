package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Clapplyfor;

public interface ClapplyforService {
	public List<Clapplyfor> queryClapplyfors(Map<String, Object> map);
	
	public int insertClapplyfor(Map<String, Object> map);
	
	public int updateClapplyforState(Map<String, Object> map);
}
