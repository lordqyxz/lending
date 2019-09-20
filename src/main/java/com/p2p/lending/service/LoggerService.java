package com.p2p.lending.service;

import java.util.List;
import java.util.Map;

import com.p2p.lending.entity.Log;

import com.github.pagehelper.PageInfo;

public interface LoggerService {

	PageInfo<Log> findList(Log log, Integer pageNo, Integer pageSize);

	public List<Log> findList(Map<String, Object> map);

	void create(Log log);
}
