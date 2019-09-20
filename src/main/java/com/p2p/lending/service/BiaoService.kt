package com.p2p.lending.service

import com.p2p.lending.entity.Biao
import org.springframework.stereotype.Service

@Service
interface BiaoService {

    fun findList(map: Map<String, Any>): List<Biao>

    fun delete(id: Int?): Int

    fun create(biao: Biao): Int

    operator fun get(id: Int?): Biao

    fun update(biao: Biao): Int
}
