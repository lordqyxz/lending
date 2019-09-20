package com.p2p.lending.service

import com.p2p.lending.entity.Poundage
import org.springframework.stereotype.Service

@Service
interface PoundageService {
    fun selectpa(currpage: String, findmap: Map<String, Any>): Map<String, Any>
    fun findList(map: Map<String, Any>): List<Poundage>
    fun insert(po: Poundage): Int
}
