package com.p2p.lending.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.p2p.lending.dao.InfromationDao;
import com.p2p.lending.entity.Approveitem;
import com.p2p.lending.entity.Certifrecord;
import com.p2p.lending.entity.Users;
import com.p2p.lending.service.InformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InformationServiceImpl implements InformationService {

	@Resource
	private InfromationDao dao;

	@Override
	public Users query(Map<String, Object> map) {
		Users user = dao.query(map);
		return user;
	}

	@Override
	public Users find(Map<String, Object> map) {
		Users user = dao.find(map);
		return user;
	}

	@Override
	public List<Approveitem> appquery() {
		List<Approveitem> list = dao.appquery();
		return list;
	}

	@Override
	public int addUsers(Map<String, Object> map) {
		int user = dao.addUsers(map);
		return user;
	}

	@Override
	public int addcertifrecord(Certifrecord cer) {
		int count = dao.addcertifrecord(cer);
		return count;
	}

	@Override
	public int upucertnum(Map<String, Object> map) {
		int count = dao.upucertnum(map);
		return count;
	}

	@Override
	public int updPassword(Map<String, Object> map) {
		int count = dao.updPassword(map);
		return count;
	}

	@Override
	public int updphone(Map<String, Object> map) {
		int count=dao.updphone(map);
		return count;
	}

	@Override
	public int userpay(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}
}
