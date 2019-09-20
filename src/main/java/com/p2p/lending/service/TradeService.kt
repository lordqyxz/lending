package com.p2p.lending.service

import com.p2p.lending.entity.Trade
import org.springframework.stereotype.Service

@Service
interface TradeService {
    fun selecttd(currpage: String, findmap: Map<String, Any>): Map<String, Any>
    fun addDate(td: Trade): Boolean
    fun selectMoney(uid: Int?): List<Trade>
}
