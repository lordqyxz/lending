package com.p2p.lending.service

import com.p2p.lending.entity.Product
import org.springframework.stereotype.Service

@Service
interface ProductService {
    fun findList(map: Map<String, Any>): List<Product>

    fun delete(id: Int?)

    fun create(product: Product)

    operator fun get(id: Int?): Product

    fun selIsExitsPrimaryKey(): Int

    fun update(product: Product): Int

    fun updateProgres(product: List<Product>)
    fun updateStatus(product: List<Product>)

    fun selList(map: Map<String, Any>): List<Product>

    /**
     * @author 陈庆山
     * @param product
     * @explain 设置修改年利率，筹款结束时间，还款时间
     */
    fun setRateAndDeadline(product: Product): Int
}
