package com.p2p.lending.impl

import com.p2p.lending.repository.InfromationDao
import com.p2p.lending.entity.Approveitem
import com.p2p.lending.entity.Certifrecord
import com.p2p.lending.entity.Users
import com.p2p.lending.service.InformationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
@Transactional
open class InformationServiceImpl : InformationService {

    @Resource
    private val dao: InfromationDao? = null

    override fun query(map: Map<String, Any?>): Users {
        return dao!!.query(map)
    }

    override fun find(map: Map<String, Any>): Users {
        return dao!!.find(map)
    }

    override fun appquery(): List<Approveitem> {
        return dao!!.appquery()
    }

    override fun addUsers(map: Map<String, Any>): Int {
        return dao!!.addUsers(map)
    }

    override fun addcertifrecord(cer: Certifrecord): Int {
        return dao!!.addcertifrecord(cer)
    }

    override fun upucertnum(map: Map<String, Any>): Int {
        return dao!!.upucertnum(map)
    }

    override fun updPassword(map: Map<String, Any>): Int {
        return dao!!.updPassword(map)
    }

    override fun updphone(map: Map<String, Any>): Int {
        return dao!!.updphone(map)
    }

    override fun userpay(map: Map<String, Any>): Int {
        // TODO Auto-generated method stub
        return 0
    }
}
