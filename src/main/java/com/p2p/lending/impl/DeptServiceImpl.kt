package com.p2p.lending.impl

import com.p2p.lending.repository.DeptDao
import com.p2p.lending.entity.Dept
import com.p2p.lending.service.DeptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class DeptServiceImpl : DeptService {

    @Autowired
    private val deptdao: DeptDao? = null

    override fun findall(): List<Dept> {
        return deptdao!!.findall()
    }

    override fun insert(dept: Dept): Int {
        return deptdao!!.insert(dept)
    }

    override fun del(did: Int?): Int {
        return deptdao!!.del(did)
    }

    override fun findbyid(did: Int?): Dept {

        return deptdao!!.findbyid(did)
    }

    override fun upd(dept: Dept): Int {

        return deptdao!!.upd(dept)
    }


}
