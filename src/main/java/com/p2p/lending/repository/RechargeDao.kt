package com.p2p.lending.repository

import com.p2p.lending.entity.Recharge


import org.springframework.stereotype.Repository

@Repository
interface RechargeDao {

    fun selectrc(map: Map<String, Any?>): List<Recharge>

    fun selectcount(map: Map<String, Any?>): Int

    fun selectall(): List<Recharge>

    fun sumczmoneyre(): Int

    fun sumdzmoneyre(): Int

}
