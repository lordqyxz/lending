package com.p2p.lending.util

import com.p2p.lending.entity.Product
import com.p2p.lending.service.ProductService
import java.text.DecimalFormat
import javax.annotation.Resource

/**
 * 计算类
 *
 * @author Administrator
 */
class Compute {

    @Resource
    internal var service: ProductService? = null

    //计算投资进度
    fun CountTwo(a: Int, b: Int): Double {

        return a.toDouble() / b
    }


    //更新投资进度

    fun updProgres(product: List<Product>) {


        for (product2 in product) {
            val money = product2.pmoney!!.toDouble()//已募集总金额
            val count = product2.ptotalmoney!!.toDouble() //总投标数
            if (money >= count) {
                product2.progress = 100.toString() + ""
            } else {
                val sum = money / count * 100
                val df = DecimalFormat("#.00")
                val result = df.format(sum)
                println("money    $money")
                println("count   $count")
                println("sum   $sum")
                product2.progress = result + ""
                println("result===   $result")
            }
            println("id   -----  " + product2.id!!)
            println("Progress  -----  " + product2.progress!!)

        }

    }

}
