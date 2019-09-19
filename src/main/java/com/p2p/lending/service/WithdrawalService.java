package com.p2p.lending.service;



import java.util.List;
import java.util.Map;

import com.p2p.lending.pojo.Approveitem;
import com.p2p.lending.pojo.Withdrawal;


public interface WithdrawalService {
	Map<String, Object>  withdrawallist(String currpage, String btn, Map<String, Object> findmap);
	List<Withdrawal> selectallw();
	Withdrawal selectone(int id);
	int updwith(int gg, int wid);
	int updwiths(int gg, int wid, String wname);
	int updmoney(int txmoney, int uid);
	int intmoney(Withdrawal w, int i);
	int sumtxmoney();
	int sumdzmoney();
	int sumsxf();
}
