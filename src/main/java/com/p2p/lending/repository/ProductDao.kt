package com.p2p.lending.repository

import com.p2p.lending.entity.Product

import org.springframework.stereotype.Repository

@Repository
interface ProductDao : BaseDao<Any, Product> {

    fun updateProgres(product: Product)

    fun updateStatus(product: Product)

    fun myList(map: Map<String, Any>): List<Product>

    /**
     * @author 陈庆山
     * @param product
     * @explain 设置修改年利率，筹款结束时间，还款时间
     */
    fun setRateAndDeadline(product: Product): Int

    //-------------募集资金到期------------------//

    fun todaoqilist(): List<Product>

    fun selIsExitsPrimaryKey(): Int

}