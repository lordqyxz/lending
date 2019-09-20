package com.p2p.lending.dao

import com.p2p.lending.entity.Userauditor

interface UserauditorDao {

    fun queryUserauditor(map: Map<String, Any>): List<Userauditor>

    fun addUserauditor(map: Map<String, Any>): Int

}
