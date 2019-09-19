package com.p2p.lending.dao;

import java.util.List;

import com.p2p.lending.pojo.Borrowcord;

public interface BorrowcordDao {
	
	public List<Borrowcord> selborr(Integer wid);
	
	
	public void updborr(Integer wid);
	
	
	public void borradd(Borrowcord borrowcord);

	
}
