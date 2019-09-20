package com.p2p.lending.dao

import com.p2p.lending.entity.Borrowcord
import com.p2p.lending.entity.Borrowmoney
import com.p2p.lending.entity.Certification
import com.p2p.lending.entity.InvestInfo
import com.p2p.lending.entity.Product
import com.p2p.lending.entity.Trade
import com.p2p.lending.entity.Users
import com.p2p.lending.entity.Withdrawal

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