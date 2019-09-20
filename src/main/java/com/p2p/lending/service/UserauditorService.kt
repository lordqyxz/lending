package com.p2p.lending.service

import com.p2p.lending.entity.Userauditor
import org.springframework.stereotype.Service

@Service
interface UserauditorService {

    fun queryUseraubitor(map: Map<String, Any?>): List<Userauditor>

    fun addUserauditor(map: Map<String, Any?>): Int
}
