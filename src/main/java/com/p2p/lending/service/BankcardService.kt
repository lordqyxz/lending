package com.p2p.lending.service

import com.p2p.lending.entity.Approveitem
import com.p2p.lending.entity.Bankcard

interface BankcardService {
    fun selectbc(currpge: String, findmap: Map<String, Any>): Map<String, Any>
}
