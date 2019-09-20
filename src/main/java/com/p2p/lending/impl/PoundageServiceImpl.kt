package com.p2p.lending.impl

import com.p2p.lending.dao.PoundageDao
import com.p2p.lending.entity.Poundage
import com.p2p.lending.service.PoundageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class PoundageServiceImpl : PoundageService {
    @Autowired
    var poundageDao: PoundageDao? = null

    override fun selectpa(currpage: String,
                          findmap: Map<String, Any>): Map<String, Any> {
        val map = HashMap<String, Any>()
        val ma = HashMap<String, Any>()
        val m = HashMap<String, Any>()
        val pagerow = 2//每页2行
        var currpages = 1//当前页
        var totalrow = 0//总行数
        m["uname"] = findmap["uname"]!!
        m["yyy"] = findmap["yyy"]!!
        m["yyyy"] = findmap["yyyy"]!!
        m["zname"] = findmap["zname"]!!
        totalrow = poundageDao!!.poundcount(m)
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
        map["uname"] = findmap["uname"]!!
        map["yyy"] = findmap["yyy"]!!
        map["yyyy"] = findmap["yyyy"]!!
        map["zname"] = findmap["zname"]!!
        val lpa = poundageDao!!.selectpa(map)
        ma["lpa"] = lpa
        ma["pagerow"] = pagerow
        ma["currpages"] = currpages
        ma["totalpage"] = totalpage
        ma["totalrow"] = totalrow
        return ma
    }

    override fun insert(po: Poundage): Int {

        return poundageDao!!.insert(po)
    }

    override fun findList(map: Map<String, Any>): List<Poundage> {
        return poundageDao!!.selectpa(map)
    }


}
