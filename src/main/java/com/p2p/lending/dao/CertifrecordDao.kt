package com.p2p.lending.dao

import com.p2p.lending.entity.Certifrecord

import org.springframework.stereotype.Repository

@Repository
interface CertifrecordDao : BaseDao<Any, Any> {

    fun queryCertifrecord(map: Map<String, Any>): List<Certifrecord>

    fun updateCertifrecord(map: Map<String, Any>): Int

    fun addCertifrecord(map: Map<String, Any>): Int

}
