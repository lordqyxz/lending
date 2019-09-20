package com.p2p.lending.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.p2p.lending.dao.LogDao
import com.p2p.lending.entity.Log
import com.p2p.lending.service.LoggerService
import com.p2p.lending.util.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class LogServiceImpl : LoggerService {

    @Autowired
    private val logDao: LogDao? = null

    override fun findList(params: Log, pageNo: Int?, pageSize: Int?): PageInfo<Log> {
        var pageNo = pageNo
        var pageSize = pageSize
        // TODO Auto-generated method stub
        // Assert.notNull(params);
        pageNo = pageNo ?: 1
        pageSize = pageSize ?: 3
        PageHelper.startPage<Any>(pageNo, pageSize)

        val list = logDao!!.findList<Any>((BeanUtils.toMap(params) as Map<String, Any>?)!!)
        // װ
        val page = PageInfo(list)

        println(" PageNum " + page.pageNum)
        println("PageSize " + page.pageSize)
        println("StartRow " + page.startRow)
        println("EndRow " + page.endRow)
        println("Total " + page.total)
        println("Pages " + page.pages)
        println("FirstPages  " + page.firstPage)
        println("LastPage " + page.lastPage)
        println("isHasPreviousPage " + page.isHasPreviousPage)
        println("HasNextPage " + page.isHasNextPage)

        return page
    }

    override fun create(log: Log) {

        logDao!!.create(log)

    }

    override fun findList(map: Map<String, Any>): List<Log> {
        // TODO Auto-generated method stub
        println("map   =========  $map")
        return logDao!!.findList(map)
    }

}
