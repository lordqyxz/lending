package com.p2p.lending.repository

import com.p2p.lending.entity.Approveitem
import org.springframework.stereotype.Repository


/**
 * @Name: ApproveDao
 * @Description:认证项设置的Dao层
 */


@Repository
interface ApproveDao {
    /**
     * Description：根据条件获取认证项，如果条件为空则放回所有认证项
     *
     * @param map
     * @return List
     */
    fun queryApproves(map: Map<String, Any?>): List<Approveitem>

    /**
     * Description：添加新认证项
     *
     * @param map
     */
    fun addApproves(map: Map<String, Any?>)

    /**
     * Description：修改认证项
     *
     * @param map
     */
    fun updateApproves(map: Map<String, Any?>)
}
