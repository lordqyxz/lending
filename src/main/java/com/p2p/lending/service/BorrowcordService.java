package com.p2p.lending.service;

import java.util.List;

import com.p2p.lending.pojo.Borrowcord;

public interface BorrowcordService {
	public List<Borrowcord> selborr(Integer wid);

	public void updborr(Integer wid);

	public void borradd(String yss, Integer beyid, String fangshi);
}
