package com.p2p.lending.impl;

import java.util.List;
import java.util.Map;

import com.p2p.lending.dao.ClapplyforDao;
import com.p2p.lending.entity.Clapplyfor;
import com.p2p.lending.service.ClapplyforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class ClapplyforServiceImpl implements ClapplyforService{
	@Autowired
	private ClapplyforDao clapplyforDao;

	@Override
	public List<Clapplyfor> queryClapplyfors(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return clapplyforDao.queryClapplyfors(map);
	}

	@Override
	public int insertClapplyfor(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateClapplyforState(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return clapplyforDao.updateClapplyforState(map);
	}

}
