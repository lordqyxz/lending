package com.p2p.lending.impl

import com.p2p.lending.dao.BidDao
import com.p2p.lending.entity.InvestInfo
import com.p2p.lending.entity.Product
import com.p2p.lending.entity.Trade
import com.p2p.lending.service.BidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class BidServiceImpl : BidService {

    @Autowired
    private val bidDao: BidDao? = null


    override fun tosize(): Int? {
        return bidDao!!.tosize().size
    }

    override fun tosizew(): Int? {
        return bidDao!!.tosizew().size
    }

    override fun tosizeb(): Int? {
        return bidDao!!.tosizeb().size
    }

    override
            /** 查询当日到期的标  */
    fun todaoqi(): List<Product> {
        return bidDao!!.todaoqi()
    }

    override
            /** 修改标状态为完成  */
    fun upzt(id: Int?) {
        bidDao!!.upzt(id)
    }

    override
            /** 查询投资记录  */
    fun totouzilist(id: Int?): List<InvestInfo> {
        return bidDao!!.totouzilist(id)
    }

    override
            /**
             * 标到期
             *
             * 总处理方法 1.加余额 2.加交易表 3.改状态
             */
    fun chuli() {
        val pList = bidDao!!.todaoqi()
        if (pList.size != 0 && pList != null) {
            for (product in pList) {
                val list = bidDao.totouzilist(product.id)
                for (investInfo in list) {
                    // 金额
                    val certification = bidDao.togetyue(investInfo.brrowid)
                    val map = HashMap<String, String>(2)
                    var ye: Int? = Integer.parseInt(certification.ctotalmoney!!)// 余额

                    println(">>>>>>>>>>>计算前:" + ye!!)
                    // 余额计算__________(添加时间日期)
                    val b = investInfo.inmoney!!.toInt() * Integer.parseInt(product.pincome!!)// 投资金额
                    //					int b = ((investInfo.getInmoney()).intValue() * (Integer.parseInt(product.getPincome())))*(/12);// 投资金额
                    ye = ye + b
                    println(">>>>>>>>>>>计算后:$ye")
                    map["ctotalmoney"] = ye.toString() + ""
                    map["cserial"] = investInfo.brrowid.toString() + ""
                    bidDao.toupyue(map)

                    // *****添加到交易记录表*******//
                    val us = bidDao.seluesr(investInfo.brrowid)

                    val trade = Trade(investInfo.brrowid, us.unickname!!, us.uname!!, "+$b",
                            product.pname!! + "标到期,返回本金和收益.", Date(), " ")
                    bidDao.toaddtrade(trade)

                }
                // 3.修改标的状态为 :4完成
                bidDao.upzt(product.id)
            }
        }
    }

    /**借款到期 */
    override fun chuli2() {
        //查询到期的标
        val liProducts = bidDao!!.togetck()
        if (liProducts.size != 0 && liProducts != null) {
            for (product in liProducts) {
                //查询投资记录表
                val list = bidDao.totouzilist(product.id)
                for (investInfo in list) {
                    //根据用户查询到账户余额
                    val certification = bidDao.togetyue(investInfo.brrowid)
                    // 余额计算
                    var ye: Int? = Integer.parseInt(certification.ctotalmoney!!)// 余额
                    println(">>>>>>>>>>>计算前:" + ye!!)
                    val b = investInfo.inmoney!!.toInt()// 投资金额
                    ye = ye + b
                    println(">>>>>>>>>>>计算后:$ye")
                    //余额修改
                    val map = HashMap<String, String>(2)
                    map["ctotalmoney"] = ye.toString() + ""
                    map["cserial"] = investInfo.brrowid.toString() + ""
                    bidDao.toupyue(map)

                    // *****添加到交易记录表*******//
                    val us = bidDao.seluesr(investInfo.brrowid)
                    val trade = Trade(investInfo.userid, us.unickname!!, us.uname!!, "+$b",
                            product.pname!! + "标资金筹集未完成,退回本金.", Date(), " ")
                    bidDao.toaddtrade(trade)
                }
                // 修改标的状态为 :3 失效
                bidDao.upzts(product.id)
            }
        }
    }

}
