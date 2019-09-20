package com.p2p.lending.service;

import java.util.List;

import com.p2p.lending.entity.Borrowcord;
import com.p2p.lending.entity.Borrowmoney;
import com.p2p.lending.entity.InvestInfo;
import com.p2p.lending.entity.Product;

public interface BidService {

	public List<Product> todaoqi();
	public void upzt(Integer id);
	public List<InvestInfo> totouzilist(Integer id);
	
	
	public void chuli();
	public void chuli2();
	
	//-----------后台展示页面-----------//
	public Integer tosize();
	public Integer tosizew();
	public Integer tosizeb();


}
