package com.p2p.lending.dao

import com.p2p.lending.entity.Recharge


interface RechargeDao {

    fun selectrc(map: Map<String, Any>): List<Recharge>

    fun selectcount(map: Map<String, Any>): Int

    fun selectall(): List<Recharge>

    fun sumczmoneyre(): Int

    fun sumdzmoneyre(): Int

}
