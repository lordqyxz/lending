package com.p2p.lending.impl

import com.p2p.lending.dao.LimitDao
import com.p2p.lending.entity.Limi
import com.p2p.lending.service.LimitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class LimitServiceImpl : LimitService {

    @Autowired
    private val limitdao: LimitDao? = null

    override fun limitByeid(eid: Int): List<*> {

        return limitdao!!.limitByeid(eid)
    }


    override fun limitdel(eid: Int?): Boolean {

        return limitdao!!.limitdel(eid)
    }

    override fun limitadd(limi: Limi): Boolean {

        return limitdao!!.limitadd(limi)
    }


}
