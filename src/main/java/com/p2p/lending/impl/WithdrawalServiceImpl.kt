package com.p2p.lending.impl


import com.p2p.lending.repository.WithdrawalDao
import com.p2p.lending.entity.Withdrawal
import com.p2p.lending.service.WithdrawalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class WithdrawalServiceImpl : WithdrawalService {
    @Autowired
    var withdrawalDao: WithdrawalDao? = null

    override fun withdrawallist(currpage: String, btn: String, findmap: Map<String, Any>): Map<String, Any> {

        val map = HashMap<String, Any>()
        val m = HashMap<String, Any?>()
        val ma = HashMap<String, Any?>()
        val pagerow = 2//每页2行
        var currpages = 1//当前页
        var totalpage = 0//总页数
        ma["btn"] = btn
        ma["wname"] = findmap["wname"]
        ma["yyy"] = findmap["yyy"]
        ma["yyyy"] = findmap["yyyy"]
        ma["wstatu"] = findmap["wstatu"]
        val totalrow = withdrawalDao!!.withdrawalcount(ma)//总行数
        totalpage = (totalrow + pagerow - 1) / pagerow
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        if (currpages < 1) {
            currpages = 1
        }
        if (currpages > totalpage) {
            currpages = totalpage
        }
        val l1 = (currpages - 1) * pagerow

        m["l1"] = l1
        m["l2"] = pagerow
        m["btn"] = btn
        m["wname"] = findmap["wname"]
        m["yyy"] = findmap["yyy"]
        m["yyyy"] = findmap["yyyy"]
        m["wstatu"] = findmap["wstatu"]
        val llist = withdrawalDao!!.withdrawallist(m)
        map["pagerow"] = pagerow
        map["currpages"] = currpages
        map["llist"] = llist
        map["totalpage"] = totalpage
        map["totalrow"] = totalrow
        return map
    }

    override fun selectallw(): List<Withdrawal> {
        return withdrawalDao!!.selectallw()
    }

    override fun selectone(id: Int): Withdrawal {
        return withdrawalDao!!.selectone(id)
    }


    override fun sumtxmoney(): Int {
        return withdrawalDao!!.sumtxmoney()
    }

    override fun sumdzmoney(): Int {
        return withdrawalDao!!.sumdzmoney()
    }

    override fun sumsxf(): Int {
        return withdrawalDao!!.sumsxf()
    }

    override fun updwith(gg: Int, wid: Int): Int {
        val map = HashMap<String, Any>()
        map["gg"] = gg
        map["wid"] = wid
        return withdrawalDao!!.updwith(map)
    }

    override fun updwiths(gg: Int, wid: Int, wname: String): Int {
        val map = HashMap<String, Any>()
        map["gg"] = gg
        map["wid"] = wid
        map["shwho"] = wname
        map["zztime"] = Date()
        map["shtime"] = Date()
        return withdrawalDao!!.updwiths(map)
    }

    override fun updmoney(txmoney: Int, uid: Int): Int {
        val map = HashMap<String, Any>()
        map["txmoney"] = txmoney
        map["uid"] = uid
        return withdrawalDao!!.updmoney(map)
    }

    override fun intmoney(w: Withdrawal, i: Int): Int {
        val map = HashMap<String, Any>()
        map["uid"] = w.getuID()!!
        map["uname"] = w.uname!!
        map["zname"] = w.zname!!
        map["jymoney"] = w.txmoney!!
        if (i == 0) {
            map["what"] = "提现失败"
        } else if (i == 1) {
            map["what"] = "转账失败"
        } else if (i == 2) {
            map["what"] = "转账成功"
        }
        map["jytime"] = Date()
        map["other"] = "无"
        return withdrawalDao!!.intmoney(map)
    }


}
