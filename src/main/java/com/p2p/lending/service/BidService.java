package com.p2p.lending.service;

import java.util.List;

import com.p2p.lending.pojo.Borrowcord;
import com.p2p.lending.pojo.Borrowmoney;
import com.p2p.lending.pojo.InvestInfo;
import com.p2p.lending.pojo.Product;

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
