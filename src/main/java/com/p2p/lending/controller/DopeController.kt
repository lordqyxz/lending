package com.p2p.lending.controller

import com.p2p.lending.service.DopeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
class DopeController {
    @Autowired
    private val dopeService: DopeService? = null

    //分页查询
    @RequestMapping("queryDope")
    fun queryDope(model: Model, @RequestParam(value = "currpage", required = false) conent: String?): String {
        val pagecount = 10//每页显示行数
        var currpage = 1//当前行数
        var totalPage = 0//总页数
        var totalRow = 0//总行数
        //获取总行数
        totalRow = dopeService!!.total().size
        //分页
        totalPage = (totalRow + pagecount - 1) / pagecount
        if (conent != null && "" != conent) {
            currpage = Integer.parseInt(conent)
        }
        if (currpage < 1) {
            currpage = 1
        }
        if (currpage > totalPage) {
            currpage = totalPage
        }
        val candp = (currpage - 1) * pagecount
        val map = HashMap<String, Any>()
        map["pagecount"] = pagecount
        map["currpage"] = candp
        val list = dopeService.findDope(map)
        model.addAttribute("list", list)
        model.addAttribute("pagecount", pagecount)
        model.addAttribute("currpage", currpage)
        model.addAttribute("totalPage", totalPage)
        model.addAttribute("totalRow", totalRow)
        return "messages"
    }

    //批量删除
    @RequestMapping(value = ["/batchDeletes"], method = [RequestMethod.POST])
    fun batchDeletes(@RequestParam(value = "delitems", required = false) items: String): String {
        println("传来的id:$items")
        val item = items.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val list = ArrayList<Any>()
        for (i in item.indices) {
            list.add(item[i])
        }
        for (i in list.indices) {

            dopeService!!.batchDeletes(Integer.parseInt(list[i] as String))
        }
        return "redirect:/queryDope.do"
    }
}
