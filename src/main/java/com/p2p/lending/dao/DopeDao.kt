package com.p2p.lending.dao

import com.p2p.lending.entity.Dope

interface DopeDao {
    fun insert(dope: Dope): Int
    //分页查询
    fun findDope(map: Map<String, Any>): List<Dope>

    //查询总行数
    fun total(): List<*>

    //删除
    fun batchDeletes(did: Int?)
}
