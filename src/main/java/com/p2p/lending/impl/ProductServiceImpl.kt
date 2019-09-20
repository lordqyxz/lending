package com.p2p.lending.impl

import com.p2p.lending.repository.DetailsDao
import com.p2p.lending.repository.ProductDao
import com.p2p.lending.entity.Product
import com.p2p.lending.service.ProductService
import com.p2p.lending.util.TimeCompare
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.text.DecimalFormat
import java.util.*
import javax.annotation.Resource

@Service
@Transactional
open class ProductServiceImpl : ProductService {
    @Autowired
    internal var productDao: ProductDao? = null

    @Resource
    internal var detailsDao: DetailsDao? = null

    override fun get(id: Int?): Product {
        // TODO Auto-generated method stub
        return productDao!![id!!]
    }

    override fun delete(id: Int?) {
        // TODO Auto-generated method stub
        detailsDao!!.deleteByPid(id)
        productDao!!.delete(id!!)
    }
/* 错误代码*/
    override fun create(product: Product) {
        // 这里设置了新增主表的时候返回主表的主键ID,为了把这个主键的ID加给副表的主键(返回主表的ID在xml那里已经配置了)
        productDao!!.create(product)
        // 新增标的时候需要级联新增详情表
        val details = product.details
        // 把主表的ID给附表的ID，让他们的值一样
        if (details != null) {
            details!!.pid = product.id
            detailsDao!!.create(details!!)
        }
    }

    override fun update(product: Product): Int {
        // TODO Auto-generated method stub

        return productDao!!.update(product)
    }

    override fun findList(map: Map<String, Any>): List<Product> {
        return productDao!!.findList(map)
    }

    override fun updateProgres(product: List<Product>) {

        for (product2 in product) {
            if (product2.ptotalmoney == null || product2.ptotalmoney == 0//""
                    || product2.pmoney == 0) {
                product2.ptotalmoney = 0
                product2.pmoney = 1
            }
            val money = product2.pmoney!!.toDouble()// 已募集总金额
            val count = product2.ptotalmoney!!.toDouble() // 总投标数
            if (money >= count) {
                product2.progress = 100.toString() + ""
            } else {
                val sum = money / count * 100
                val df = DecimalFormat("#.00")
                var result = df.format(sum)
                if (result.length < 4) {
                    result = "0$result"
                }
                product2.progress = result + ""
            }

            productDao!!.updateProgres(product2)
        }
    }

    override fun updateStatus(product: List<Product>) {

        for (product2 in product) {
            val pragess = java.lang.Double.parseDouble(product2.progress!!)
            val ptime = product2.ptime// 项目期限
            val time = TimeCompare()
            val flag = time.Compare(Date().toLocaleString(), ptime!!.toLocaleString())
            if (pragess < 100 && flag == 1) {
                product2.pstate = "1"// 筹集中
            } else if (pragess >= 100 && flag == -1) {
                product2.pstate = "2"// 筹集完
            } else if (pragess < 100 && flag == 0) {
                product2.pstate = "3"// 失效
            }
            productDao!!.updateStatus(product2)
        }
    }

    /**
     * @author 陈庆山
     * @param product
     * @explain 设置修改年利率，筹款结束时间，还款时间
     */

    override fun setRateAndDeadline(product: Product): Int {
        // TODO Auto-generated method stub
        return productDao!!.setRateAndDeadline(product)
    }

    override fun selList(map: Map<String, Any>): List<Product> {
        return productDao!!.myList(map)
    }

    override fun selIsExitsPrimaryKey(): Int {
        // TODO Auto-generated method stub
        return productDao!!.selIsExitsPrimaryKey()
    }

}
