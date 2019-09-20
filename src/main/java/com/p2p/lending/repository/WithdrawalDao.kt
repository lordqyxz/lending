package com.p2p.lending.repository


import com.p2p.lending.entity.Withdrawal


import org.springframework.stereotype.Repository

@Repository
interface WithdrawalDao {

    fun withdrawallist(map: Map<String, Any?>): List<Withdrawal>

    fun withdrawalcount(ma: Map<String, Any?>): Int

    fun selectallw(): List<Withdrawal>

    fun selectone(id: Int): Withdrawal

    fun updwith(ma: Map<String, Any>): Int

    fun updwiths(ma: Map<String, Any>): Int

    fun updmoney(ma: Map<String, Any>): Int

    fun intmoney(ma: Map<String, Any>): Int

    fun sumtxmoney(): Int

    fun sumdzmoney(): Int

    fun sumsxf(): Int

}
