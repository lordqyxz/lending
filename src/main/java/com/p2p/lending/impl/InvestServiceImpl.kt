package com.p2p.lending.impl

import com.p2p.lending.dao.InvestInfoDao
import com.p2p.lending.entity.InvestInfo
import com.p2p.lending.service.InvestService
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class InvestServiceImpl : InvestService {

    @Resource
    private val iid: InvestInfoDao? = null

    override fun investA(ii: InvestInfo): Int {

        return iid!!.create(ii)
    }

    override fun investS(map: Map<String, Any>): List<InvestInfo> {

        return iid!!.recordList(map)
    }

    override fun getDtail(map: Map<String, InvestInfo>): List<InvestInfo> {
        // TODO Auto-generated method stub
        return iid!!.getDtail(map)
    }

    override fun sumMoney(map: Map<String, Any>): Double? {
        return iid!!.getSum(map)
    }

    override fun getMoney(uid: Int?): Int? {
        return iid!!.getMoney(uid)
    }

}
