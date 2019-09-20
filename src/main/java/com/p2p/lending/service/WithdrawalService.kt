package com.p2p.lending.service


import com.p2p.lending.entity.Withdrawal
import org.springframework.stereotype.Service

@Service
interface WithdrawalService {
    fun withdrawallist(currpage: String, btn: String, findmap: Map<String, Any>): Map<String, Any>
    fun selectallw(): List<Withdrawal>
    fun selectone(id: Int): Withdrawal
    fun updwith(gg: Int, wid: Int): Int
    fun updwiths(gg: Int, wid: Int, wname: String): Int
    fun updmoney(txmoney: Int, uid: Int): Int
    fun intmoney(w: Withdrawal, i: Int): Int
    fun sumtxmoney(): Int
    fun sumdzmoney(): Int
    fun sumsxf(): Int
}
