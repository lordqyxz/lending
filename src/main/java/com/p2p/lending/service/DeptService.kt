package com.p2p.lending.service

import com.p2p.lending.entity.Dept

interface DeptService {
    fun findall(): List<Dept>

    fun findbyid(did: Int?): Dept

    fun insert(dept: Dept): Int

    fun del(did: Int?): Int

    fun upd(dept: Dept): Int
}
