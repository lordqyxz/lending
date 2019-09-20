package com.p2p.lending.service

import com.p2p.lending.entity.Userauditor

interface UserauditorService {

    fun queryUseraubitor(map: Map<String, Any>): List<Userauditor>

    fun addUserauditor(map: Map<String, Any>): Int
}
