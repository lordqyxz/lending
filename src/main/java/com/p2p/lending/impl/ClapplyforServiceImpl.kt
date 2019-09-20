package com.p2p.lending.impl

import com.p2p.lending.entity.Clapplyfor
import com.p2p.lending.repository.ClapplyforDao
import com.p2p.lending.service.ClapplyforService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ClapplyforServiceImpl : ClapplyforService {
    @Autowired
    private val clapplyforDao: ClapplyforDao? = null

    override fun queryClapplyfors(map: Map<String, Any?>): List<Clapplyfor> {
        // TODO Auto-generated method stub
        return clapplyforDao!!.queryClapplyfors(map)
    }

    override fun insertClapplyfor(map: Map<String, Any?>): Int {
        // TODO Auto-generated method stub
        return 0
    }

    override fun updateClapplyforState(map: Map<String, Any?>): Int {
        // TODO Auto-generated method stub
        return clapplyforDao!!.updateClapplyforState(map)
    }

}
