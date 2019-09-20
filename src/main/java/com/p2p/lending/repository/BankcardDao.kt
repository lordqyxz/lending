package com.p2p.lending.repository

import com.p2p.lending.entity.Bankcard
import org.springframework.stereotype.Repository

@Repository
interface BankcardDao {

    fun selectbc(map: Map<String, Any?>): List<Bankcard>

    fun bankcount(map: Map<String, Any?>): Int
}
