package com.p2p.lending.dao;

import java.util.List;
import java.util.Map;

import com.p2p.lending.pojo.Borrowcord;
import com.p2p.lending.pojo.Borrowmoney;
import com.p2p.lending.pojo.Certification;
import com.p2p.lending.pojo.InvestInfo;
import com.p2p.lending.pojo.Product;
import com.p2p.lending.pojo.Trade;
import com.p2p.lending.pojo.Users;
import com.p2p.lending.pojo.Withdrawal;

public interface BidDao {

	public List<Product> todaoqi();

	public void upzt(Integer id);

	public List<InvestInfo> totouzilist(Integer id);
	
	public Users seluesr(Integer uid);
	

	// ------------项目到期----------------//
	
	public Certification togetyue(Integer uID);

	public void toupyue(Map<String, String> map);

	public void toaddtrade(Trade trade);
	
	// ------------筹款到期----------------//
	
	public List<Product> togetck();
	
	public void upzts(Integer id);
	
	// ------------首页展示信息----------------//
	
	public List<Borrowmoney> tosize();
	public List<Withdrawal> tosizew();
	public List<Borrowcord> tosizeb();

}