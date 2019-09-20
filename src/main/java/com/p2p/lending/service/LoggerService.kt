package com.p2p.lending.service

import com.github.pagehelper.PageInfo
import com.p2p.lending.entity.Log
import org.springframework.stereotype.Service

@Service
interface LoggerService {

    fun findList(log: Log, pageNo: Int?, pageSize: Int?): PageInfo<Log>

    fun findList(map: Map<String, Any>): List<Log>

    fun create(log: Log)
}
