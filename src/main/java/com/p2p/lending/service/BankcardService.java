package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Approveitem;
import com.p2p.lending.entity.Bankcard;

public interface BankcardService {
	Map<String, Object> selectbc(String currpge, Map<String, Object> findmap);
}
