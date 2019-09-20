package com.p2p.lending.dao

import com.p2p.lending.entity.Borrowmoney
import com.p2p.lending.entity.Creditlimit

import org.springframework.stereotype.Repository

@Repository
interface CreditlimitDao : BaseDao<Any, Borrowmoney> {

    fun queryCreditlimits(map: Map<String, Any>): List<Creditlimit>

    fun insertCreditlimit(map: Map<String, Any>): Int

    fun updateCreditlimit(map: Map<String, Any>): Int

}
