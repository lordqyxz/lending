package com.p2p.lending.impl

import com.p2p.lending.dao.CertifrecordDao
import com.p2p.lending.entity.Certifrecord
import com.p2p.lending.service.CertifrecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class CertifrecordServiceImpl : CertifrecordService {
    @Autowired
    private val certifrecordDao: CertifrecordDao? = null

    override fun queryCertifrecord(map: Map<String, Any>): List<Certifrecord> {
        // TODO Auto-generated method stub
        return certifrecordDao!!.queryCertifrecord(map)
    }

    override fun updateCertifrecord(map: Map<String, Any>): Int {
        // TODO Auto-generated method stub
        return certifrecordDao!!.updateCertifrecord(map)
    }

    override fun addCertifrecord(map: Map<String, Any>): Int {
        // TODO Auto-generated method stub
        return certifrecordDao!!.addCertifrecord(map)
    }

}
