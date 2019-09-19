package com.p2p.lending.ount;

import com.p2p.lending.service.BidService;
import com.p2p.lending.impl.BidServiceImpl;

/**
 * 
 * @author 周旗 标到期计算类 分为募集资金到期 和 还款到期
 */
public class BidCount {
	BidService bid = new BidServiceImpl();

	/** 项目到期处理 */
	public void toRaiseMoney() {
		bid.chuli();
	}
	
	/** 筹款到期处理 */
	public void to() {
		bid.chuli2();
	}

}
