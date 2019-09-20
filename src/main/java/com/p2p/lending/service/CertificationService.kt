package com.p2p.lending.service

import com.p2p.lending.entity.Certification
import org.springframework.stereotype.Service

@Service
interface CertificationService {
    fun selectM(uid: Int?): String
    fun updateM(map: Map<String, Any>): Boolean
    fun undate(map: Map<String, Any>): Boolean
    fun upm(map: Map<String, Any>): Boolean
    fun insert(cer: Certification): Int
    fun select(uid: Int?): Certification
}
