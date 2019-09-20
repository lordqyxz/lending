package com.p2p.lending.impl

import com.p2p.lending.repository.ApproveDao
import com.p2p.lending.entity.Approveitem
import com.p2p.lending.service.ApproveService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Name: ApproveServiceImpl
 * @Description:认证项设置的Service的实现层
 * @author chenqingshan
 * @Date: 2017-2-20 Time: 20:24
 */
@Service
@Transactional
open class ApproveServiceImpl : ApproveService {
    @Autowired
    private val approveDao: ApproveDao? = null

    /**
     * Description：根据条件获取认证项，如果条件为空则放回所有认证项
     *
     * @return List
     */

    override fun queryApproves(map: Map<String, Any>): List<Approveitem> {
        // TODO Auto-generated method stub
        return approveDao!!.queryApproves(map)
    }

    /**
     * Description：修改认证项
     *
     * @param map
     */
    override fun updateApproves(map: Map<String, Any>) {
        // TODO Auto-generated method stub
        approveDao!!.updateApproves(map)
    }

    /**
     * Description：添加认证项
     *
     * @param map
     */
    override fun addApproves(map: Map<String, Any>) {
        // TODO Auto-generated method stub
        approveDao!!.addApproves(map)
    }

}
