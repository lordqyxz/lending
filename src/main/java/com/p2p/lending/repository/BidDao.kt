package com.p2p.lending.repository

import com.p2p.lending.entity.*
import org.springframework.stereotype.Repository

@Repository
interface BidDao {

    fun todaoqi(): List<Product>

    fun upzt(id: Int?)

    fun totouzilist(id: Int?): List<InvestInfo>

    fun seluesr(uid: Int?): Users


    // ------------项目到期----------------//

    fun togetyue(uID: Int?): Certification

    fun toupyue(map: Map<String, String>)

    fun toaddtrade(trade: Trade)

    // ------------筹款到期----------------//

    fun togetck(): List<Product>

    fun upzts(id: Int?)

    // ------------首页展示信息----------------//

    fun tosize(): List<Borrowmoney>
    fun tosizew(): List<Withdrawal>
    fun tosizeb(): List<Borrowcord>

}