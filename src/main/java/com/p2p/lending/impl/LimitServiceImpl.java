package com.p2p.lending.impl;

import java.util.List;

import com.p2p.lending.dao.LimitDao;
import com.p2p.lending.entity.Limi;
import com.p2p.lending.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class LimitServiceImpl implements LimitService{
	
	@Autowired
	private LimitDao limitdao;
	
	public List limitByeid(int eid) {
		
		return limitdao.limitByeid(eid);
	}


	public boolean limitdel(Integer eid) {
		
		return limitdao.limitdel(eid);
	}

	public boolean limitadd(Limi limi) {
		
		return limitdao.limitadd(limi);
	}


}
