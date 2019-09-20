package com.p2p.lending.dao

import com.p2p.lending.entity.Details

import org.springframework.stereotype.Repository

@Repository
interface DetailsDao : BaseDao<Any, Details> {
    fun detailslist(ids: Int?): List<Details>

    fun detailsadd(notice: Details)

    fun detailsget(ids: Int?): Details

    fun detailsdel(ids: Int?)

    fun deleteByPid(pid: Int?)


}
