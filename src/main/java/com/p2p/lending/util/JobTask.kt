package com.p2p.lending.util

import com.p2p.lending.entity.Product
import com.p2p.lending.ount.BidCount
import com.p2p.lending.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class JobTask {

    @Autowired
    private val productService: ProductService? = null

    /**
     * 定时计算。每天凌晨 01:00 执行一次
     */
    @Scheduled(cron = "0 0 1 * * *")
    fun show() {
        val count = BidCount()
        count.toRaiseMoney()
    }

    /**
     * 心跳更新。启动时执行一次，之后每隔2分钟执行一次
     */
    @Scheduled(fixedRate = (1000 * 1300000).toLong())
    fun print() {
        val product = Product()
        val list2 = productService!!.findList(BeanUtils.toMap(product)!!)
        productService.updateProgres(list2)
    }

    @Scheduled(fixedRate = (1000 * 140000).toLong())
    fun Sum() {

    }

    @Scheduled(fixedRate = (1000 * 15000000).toLong())
    fun updateStatus() {
        val product = Product()
        val list2 = productService!!.findList(BeanUtils.toMap(product)!!)
        println("跟新状态。。。。。。。。。。。。")
        productService.updateStatus(list2)
    }

    companion object {

        private val CRnumber = 0
    }
}
