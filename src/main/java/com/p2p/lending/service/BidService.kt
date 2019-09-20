package com.p2p.lending.service

import com.p2p.lending.entity.InvestInfo
import com.p2p.lending.entity.Product
import org.springframework.stereotype.Service

@Service
interface BidService {

    fun todaoqi(): List<Product>
    fun upzt(id: Int?)
    fun totouzilist(id: Int?): List<InvestInfo>


    fun chuli()
    fun chuli2()

    //-----------后台展示页面-----------//
    fun tosize(): Int?

    fun tosizew(): Int?
    fun tosizeb(): Int?


}
