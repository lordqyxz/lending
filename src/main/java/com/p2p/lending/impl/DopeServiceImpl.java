package com.p2p.lending.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.p2p.lending.dao.DopeDao;
import com.p2p.lending.pojo.Dope;
import com.p2p.lending.service.DopeService;
import org.springframework.stereotype.Service;
@Service
public class DopeServiceImpl implements DopeService{
	@Resource
	private DopeDao dop;
	@Override
	public int insert(Dope dope) {
		// TODO Auto-generated method stub
		return dop.insert(dope);
	}
	@Override
	public List<Dope> findDope(Map<String, Object> map) {
		List<Dope> list=dop.findDope(map);
		return list;
	}
	@Override
	public List total() {
		List list=dop.total();
		return list;
	}
	@Override
	public void batchDeletes(Integer did) {
		dop.batchDeletes(did);
	}

}
