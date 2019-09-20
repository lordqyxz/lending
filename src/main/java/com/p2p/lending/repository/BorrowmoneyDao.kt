package com.p2p.lending.repository

import com.p2p.lending.entity.Borrowmoney

import org.springframework.stereotype.Repository

@Repository
interface BorrowmoneyDao : BaseDao<Any, Borrowmoney> {
    // ygx
    fun pagingList(map: Map<String, Any>): List<Borrowmoney>

    //周旗  2017年3月2日09:42:30
    fun toaddborr(borrowmoney: Borrowmoney)

    fun updhuankuan(): List<Borrowmoney>
    fun borrowget(ids: Int?): Borrowmoney
    //hjy
    fun selecthjy(map: Map<String, Any>): List<Borrowmoney>

    fun hjycount(): Int
}