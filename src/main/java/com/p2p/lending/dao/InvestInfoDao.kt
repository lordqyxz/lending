package com.p2p.lending.dao

import org.apache.ibatis.annotations.Param
import com.p2p.lending.entity.InvestInfo

interface InvestInfoDao : BaseDao<Any, InvestInfo> {

    fun recordList(map: Map<String, Any>): List<InvestInfo>

    fun getSum(map: Map<String, Any>): Double?

    fun getDtail(@Param("params") params: Map<String, InvestInfo>): List<InvestInfo>

    fun getMoney(uid: Int?): Int?
}
