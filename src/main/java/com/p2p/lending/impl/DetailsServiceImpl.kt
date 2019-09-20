package com.p2p.lending.impl

import com.p2p.lending.dao.DetailsDao
import com.p2p.lending.entity.Details
import com.p2p.lending.service.DetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

/**
 *
 * @author 周旗 网站消息通告 控制层
 */
@Service
@Transactional
open class DetailsServiceImpl : DetailsService {

    @Resource
    private val detailsDao: DetailsDao? = null


    override fun detailslist(pid: Int?): List<Details> {
        return detailsDao!!.detailslist(pid)
    }

    override fun delete(id: Int?): Int {
        // TODO Auto-generated method stub
        return detailsDao!!.delete(id!!)
    }

    override fun create(details: Details): Int {
        // TODO;
        return detailsDao!!.create(details)
    }

    override fun get(id: Int?): Details {
        // TODO Auto-generated method stub
        return detailsDao!![id!!]
    }

    override fun update(record: Details): Int {
        // TODO Auto-generated method stub
        return 0
    }

    override fun deleteByPid(pid: Int?) {
        // TODO Auto-generated method stub
        detailsDao!!.deleteByPid(pid)
    }
}
