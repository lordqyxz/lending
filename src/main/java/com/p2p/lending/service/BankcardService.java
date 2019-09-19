package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.pojo.Approveitem;
import com.p2p.lending.pojo.Bankcard;

public interface BankcardService {
	Map<String, Object> selectbc(String currpge, Map<String, Object> findmap);
}
