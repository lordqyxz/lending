package com.p2p.lending.service

import com.p2p.lending.entity.Recharge


interface RechargeService {
    fun selectrc(currpage: String, findmap: Map<String, Any>): Map<String, Any>
    fun selectall(): List<Recharge>
    fun sumczmoneyre(): Int
    fun sumdzmoneyre(): Int
}
