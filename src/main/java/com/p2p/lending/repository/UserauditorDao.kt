package com.p2p.lending.repository

import com.p2p.lending.entity.Userauditor

import org.springframework.stereotype.Repository

@Repository
interface UserauditorDao {

    fun queryUserauditor(map: Map<String, Any?>): List<Userauditor>

    fun addUserauditor(map: Map<String, Any?>): Int

}
