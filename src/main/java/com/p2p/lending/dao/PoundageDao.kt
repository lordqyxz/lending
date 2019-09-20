package com.p2p.lending.dao

import com.p2p.lending.entity.Poundage


interface PoundageDao {

    fun selectpa(map: Map<String, Any>): List<Poundage>

    fun poundcount(map: Map<String, Any>): Int

    fun insert(po: Poundage): Int
}
