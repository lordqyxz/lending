package com.p2p.lending.impl

import com.p2p.lending.repository.CreditlimitDao
import com.p2p.lending.entity.Creditlimit
import com.p2p.lending.service.CreditlimitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class CreditlimitServiceImpl : CreditlimitService {

    @Autowired
    private val creditlimitDao: CreditlimitDao? = null

    override fun queryCreditlimits(map: Map<String, Any>): List<Creditlimit> {
        // TODO Auto-generated method stub
        return creditlimitDao!!.queryCreditlimits(map)
    }

    override fun updateCreditlimit(map: Map<String, Any>): Int {
        // TODO Auto-generated method stub
        return creditlimitDao!!.updateCreditlimit(map)
    }

    override fun insertCreditlimit(map: Map<String, Any>): Int {
        // TODO Auto-generated method stub
        return creditlimitDao!!.insertCreditlimit(map)
    }

}
