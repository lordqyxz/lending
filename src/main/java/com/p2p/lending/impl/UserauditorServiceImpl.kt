package com.p2p.lending.impl

import com.p2p.lending.dao.UserauditorDao
import com.p2p.lending.entity.Userauditor
import com.p2p.lending.service.UserauditorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class UserauditorServiceImpl : UserauditorService {

    @Autowired
    private val userauditorDao: UserauditorDao? = null

    override fun queryUseraubitor(map: Map<String, Any>): List<Userauditor> {
        // TODO Auto-generated method stub
        return userauditorDao!!.queryUserauditor(map)
    }

    override fun addUserauditor(map: Map<String, Any>): Int {
        // TODO Auto-generated method stub
        return userauditorDao!!.addUserauditor(map)
    }


}
