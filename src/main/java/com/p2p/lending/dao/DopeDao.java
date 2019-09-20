package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Dope;

public interface DopeDao {
	int insert(Dope dope);
	//分页查询
	public List<Dope> findDope(Map<String, Object> map);
	//查询总行数
	public List total();
	//删除
	public void batchDeletes(Integer did);
}
