package com.p2p.lending.service

import com.p2p.lending.entity.Trade


interface TradeService {
    fun selecttd(currpge: String, findmap: Map<String, Any>): Map<String, Any>
    fun addDate(td: Trade): Boolean
    fun selectMoney(uid: Int?): List<Trade>
}
