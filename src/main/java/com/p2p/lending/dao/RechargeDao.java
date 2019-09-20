package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Recharge;




public interface RechargeDao {
	List<Recharge> selectrc(Map<String, Object> map);
	int selectcount(Map<String, Object> map);
	List<Recharge> selectall();
	int sumczmoneyre();
	int sumdzmoneyre();
}
