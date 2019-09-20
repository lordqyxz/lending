package com.p2p.lending.service

import com.p2p.lending.entity.InvestInfo
import org.springframework.stereotype.Service

@Service
interface InvestService {
    fun investA(ii: InvestInfo): Int
    fun investS(map: Map<String, Any>): List<InvestInfo>
    fun sumMoney(map: Map<String, Any>): Double?

    fun getDtail(map: Map<String, InvestInfo>): List<InvestInfo>
    fun getMoney(uid: Int?): Int?
}
