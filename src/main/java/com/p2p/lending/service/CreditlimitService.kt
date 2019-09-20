package com.p2p.lending.service

import com.p2p.lending.entity.Creditlimit
import org.springframework.stereotype.Service

@Service
interface CreditlimitService {

    fun queryCreditlimits(map: Map<String, Any>): List<Creditlimit>
    fun updateCreditlimit(map: Map<String, Any>): Int
    fun insertCreditlimit(map: Map<String, Any>): Int

}
