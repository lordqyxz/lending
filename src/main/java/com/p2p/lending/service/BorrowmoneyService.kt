package com.p2p.lending.service

import com.p2p.lending.entity.Approveitem
import com.p2p.lending.entity.Borrowmoney
import com.p2p.lending.entity.Log

import com.github.pagehelper.PageInfo

interface BorrowmoneyService {

    fun findList(map: Map<String, Any>): List<Borrowmoney>

    fun findList(Borrowmoney: Borrowmoney, pageNo: Int?, pageSize: Int?): PageInfo<Borrowmoney>

    fun delete(id: Int?): Int

    fun create(record: Borrowmoney): Int

    operator fun get(id: Int?): Borrowmoney

    fun update(record: Borrowmoney): Int

    /*
	 * ygx 2017 2 25 效仿陈庆山分页
	 */
    fun pagingSel(map: Map<String, Any>): List<Borrowmoney>

    /*
	 * zq 2017年3月3日10:40:48
	 */
    fun toaddborr(borrowmoney: Borrowmoney)

    fun updhuankuan(): List<Borrowmoney>
    fun borrowget(ids: Int?): Borrowmoney


    /**
     * hjy
     */
    fun selecthjy(currpage: String): Map<String, Any>
}