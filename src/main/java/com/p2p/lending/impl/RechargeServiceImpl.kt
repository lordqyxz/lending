package com.p2p.lending.impl


import com.p2p.lending.dao.RechargeDao
import com.p2p.lending.entity.Recharge
import com.p2p.lending.service.RechargeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class RechargeServiceImpl : RechargeService {
    @Autowired
    var rechargeDao: RechargeDao? = null

    override fun selectrc(currpage: String, findmap: Map<String, Any>): Map<String, Any> {
        val map = HashMap<String, Any?>()
        val ma = HashMap<String, Any?>()
        val m = HashMap<String, Any>()
        val pagerow = 2//每页2行
        var currpages = 1//当前页
        var totalrow = 0//总行数
        ma["uname"] = findmap["uname"]
        ma["zflx"] = findmap["zflx"]
        ma["yyy"] = findmap["yyy"]
        ma["yyyy"] = findmap["yyyy"]
        ma["statu"] = findmap["statu"]
        totalrow = rechargeDao!!.selectcount(ma)
        val totalpage = (totalrow + pagerow - 1) / pagerow//总页数
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
        map["l1"] = l1
        map["l2"] = pagerow
        map["uname"] = findmap["uname"]
        map["zflx"] = findmap["zflx"]
        map["yyy"] = findmap["yyy"]
        map["yyyy"] = findmap["yyyy"]
        map["statu"] = findmap["statu"]
        val lrc = rechargeDao!!.selectrc(map)
        m["lrc"] = lrc
        m["pagerow"] = pagerow
        m["currpages"] = currpages
        m["totalrow"] = totalrow
        m["totalpage"] = totalpage
        return m
    }

    override fun selectall(): List<Recharge> {
        return rechargeDao!!.selectall()
    }

    override fun sumczmoneyre(): Int {
        return rechargeDao!!.sumczmoneyre()
    }

    override fun sumdzmoneyre(): Int {
        return rechargeDao!!.sumdzmoneyre()
    }


}
