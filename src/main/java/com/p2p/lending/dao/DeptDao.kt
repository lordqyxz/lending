package com.p2p.lending.dao

import com.p2p.lending.entity.Dept

import org.springframework.stereotype.Repository

@Repository
interface DeptDao {

    fun findall(): List<Dept>

    fun findbyid(did: Int?): Dept

    fun insert(dept: Dept): Int

    fun del(did: Int?): Int

    fun upd(dept: Dept): Int
}
