package com.p2p.lending.dao;



import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Withdrawal;


public interface WithdrawalDao {
	List<Withdrawal> withdrawallist(Map<String, Object> map);
	int withdrawalcount(Map<String, Object> ma);
	List<Withdrawal> selectallw();
	Withdrawal selectone(int id);
	int updwith(Map<String, Object> ma);
	int updwiths(Map<String, Object> ma);
	int updmoney(Map<String, Object> ma);
	int intmoney(Map<String, Object> ma);
	int sumtxmoney();
	int sumdzmoney();
	int sumsxf();
}
