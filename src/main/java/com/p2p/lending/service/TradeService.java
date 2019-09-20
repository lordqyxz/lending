package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Trade;


public interface TradeService {
	Map<String, Object> selecttd(String currpge, Map<String, Object> findmap);
	boolean addDate(Trade td);
	List<Trade> selectMoney(Integer uid);
}
