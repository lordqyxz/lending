package com.p2p.lending.service

import java.math.BigDecimal

import com.p2p.lending.entity.InvestInfo

interface InvestService {
    fun investA(ii: InvestInfo): Int
    fun investS(map: Map<String, Any>): List<InvestInfo>
    fun sumMoney(map: Map<String, Any>): Double?

    fun getDtail(map: Map<String, InvestInfo>): List<InvestInfo>
    fun getMoney(uid: Int?): Int?
}
