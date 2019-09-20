package com.p2p.lending.service;

import java.util.Map;

public interface BankcardService {
	Map<String, Object> selectbc(String currpge, Map<String, Object> findmap);
}
