package com.p2p.lending.impl

import com.p2p.lending.dao.DopeDao
import com.p2p.lending.entity.Dope
import com.p2p.lending.service.DopeService
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class DopeServiceImpl : DopeService {
    @Resource
    private val dop: DopeDao? = null

    override fun insert(dope: Dope): Int {
        // TODO Auto-generated method stub
        return dop!!.insert(dope)
    }

    override fun findDope(map: Map<String, Any>): List<Dope> {
        return dop!!.findDope(map)
    }

    override fun total(): List<*> {
        return dop!!.total()
    }

    override fun batchDeletes(did: Int?) {
        dop!!.batchDeletes(did)
    }

}
