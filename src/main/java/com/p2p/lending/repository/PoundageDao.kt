package com.p2p.lending.repository

import com.p2p.lending.entity.Poundage


import org.springframework.stereotype.Repository

@Repository
interface PoundageDao {

    fun selectpa(map: Map<String, Any?>): List<Poundage>

    fun poundcount(map: Map<String, Any?>): Int

    fun insert(po: Poundage): Int
}
