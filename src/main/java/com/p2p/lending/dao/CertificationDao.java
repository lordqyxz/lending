package com.p2p.lending.dao;

import java.util.Map;

import com.p2p.lending.entity.Certification;

public interface CertificationDao {
	public String selMoney(Integer uid); //查出某个用户金额
	public boolean updMoney(Map<String, Object> map);//修改某个用户金额
	public boolean upmoney(Map<String, Object> map);
	public int insert(Certification cer);//添加钱包数据
	public Certification select(Integer uid);//查出数据
}
