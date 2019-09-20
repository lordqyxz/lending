package com.p2p.lending.impl

import com.p2p.lending.dao.CertificationDao
import com.p2p.lending.entity.Certification
import com.p2p.lending.service.CertificationService
import org.springframework.stereotype.Service
import javax.annotation.Resource


@Service
class CertificationServiceImpl : CertificationService {

    @Resource
    internal var cd: CertificationDao? = null

    override fun selectM(uid: Int?): String {
        return cd!!.selMoney(uid)
    }

    override fun updateM(map: Map<String, Any>): Boolean {
        return cd!!.updMoney(map)
    }

    override fun insert(cer: Certification): Int {
        // TODO Auto-generated method stub
        return cd!!.insert(cer)
    }

    override fun select(uid: Int?): Certification {
        // TODO Auto-generated method stub
        return cd!!.select(uid)
    }

    override fun undate(map: Map<String, Any>): Boolean {
        // TODO Auto-generated method stub
        return cd!!.upmoney(map)
    }

    override fun upm(map: Map<String, Any>): Boolean {
        return cd!!.upmoney(map)
    }

}
