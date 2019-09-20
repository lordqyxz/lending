package com.p2p.lending.service

import com.p2p.lending.entity.Details

interface DetailsService {

    fun detailslist(pid: Int?): List<Details>

    fun delete(id: Int?): Int
    fun deleteByPid(id: Int?)
    fun create(details: Details): Int

    operator fun get(id: Int?): Details

    fun update(record: Details): Int


}
