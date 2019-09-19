package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.pojo.Trade;



public interface TradeDao {
	List<Trade> selecttd(Map<String, Object> map);
	int tradecount(Map<String, Object> map);
	boolean insertT(Trade td);
	
	List<Trade> selectMoney(Integer uid);
}
