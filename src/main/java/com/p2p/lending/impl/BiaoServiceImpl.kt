package com.p2p.lending.impl

import com.p2p.lending.dao.BiaoMapperDao
import com.p2p.lending.entity.Biao
import com.p2p.lending.service.BiaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class BiaoServiceImpl : BiaoService {

    @Autowired
    internal var biaoMapperDao: BiaoMapperDao? = null

    override fun get(id: Int?): Biao {
        // TODO Auto-generated method stub
        return biaoMapperDao!![id!!]
    }

    override fun delete(id: Int?): Int {
        // TODO Auto-generated method stub
        return biaoMapperDao!!.delete(id!!)
    }

    override fun create(record: Biao): Int {
        // TODO Auto-generated method stub
        return biaoMapperDao!!.create(record)
    }

    override fun update(record: Biao): Int {
        // TODO Auto-generated method stub
        return biaoMapperDao!!.update(record)
    }

    override fun findList(map: Map<String, Any>): List<Biao> {
        return biaoMapperDao!!.findList(map)
    }
}
