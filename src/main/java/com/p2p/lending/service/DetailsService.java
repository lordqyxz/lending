package com.p2p.lending.service;

import java.util.List;

import com.p2p.lending.pojo.Details;

public interface DetailsService {

	public List<Details> detailslist(Integer pid);

	int delete(Integer id);
	void deleteByPid(Integer id);
	int create(Details details);

	Details get(Integer id);

	int update(Details record);

	 
}
