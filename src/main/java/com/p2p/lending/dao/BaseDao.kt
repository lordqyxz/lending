package com.p2p.lending.dao

import com.p2p.lending.entity.Borrowmoney
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository


@Repository
interface BaseDao<ID : Any, Domain : Any> {

    /**
     * 根据ID查询
     *
     * @param id
     * @param vp
     * @return
     */
    operator fun get(@Param("id") id: ID): Domain

    /**
     * 删除
     *
     * @param id
     * @param vp
     */
    fun delete(@Param("id") id: ID): Int

    /**
     * 分页查询
     *
     * @param pagination
     * @param params
     * @param <V>
     * @return
    </V> */
    fun findListByPage(map: Map<String, Any>): List<Borrowmoney>

    fun <V> findList(@Param("params") params: Map<String, V>): List<Domain>

    /**
     * 保存
     *
     * @param domain
     */
    fun create(domain: Domain): Int

    /**
     * 更新
     *
     * @param domain
     */
    fun update(domain: Domain): Int

}
