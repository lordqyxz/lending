package com.p2p.lending.service

import com.p2p.lending.entity.Certifrecord

interface CertifrecordService {

    fun queryCertifrecord(map: Map<String, Any>): List<Certifrecord>

    fun updateCertifrecord(map: Map<String, Any>): Int

    fun addCertifrecord(map: Map<String, Any>): Int
}
