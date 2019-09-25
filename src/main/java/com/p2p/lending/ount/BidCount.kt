package com.p2p.lending.ount

import com.p2p.lending.impl.BidServiceImpl
import com.p2p.lending.service.BidService

/**
 * @author 周旗 标到期计算类 分为募集资金到期 和 还款到期
 */
class BidCount {
    internal var bid: BidService = BidServiceImpl()

    /**
     * 项目到期处理
     */
    fun toRaiseMoney() {
        bid.chuli()
    }

    /**
     * 筹款到期处理
     */
    fun to() {
        bid.chuli2()
    }

}
