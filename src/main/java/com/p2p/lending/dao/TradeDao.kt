package com.p2p.lending.dao

import com.p2p.lending.entity.Trade


import org.springframework.stereotype.Repository

@Repository
interface TradeDao {

    fun selecttd(map: Map<String, Any?>): List<Trade>

    fun tradecount(map: Map<String, Any?>): Int

    fun insertT(td: Trade): Boolean

    fun selectMoney(uid: Int?): List<Trade>
}
