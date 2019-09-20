package com.p2p.lending.service

import org.springframework.stereotype.Service

@Service
interface BankcardService {
    fun selectbc(currpage: String, findmap: Map<String, Any>): Map<String, Any>
}
