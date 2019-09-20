package com.p2p.lending.controller

import com.p2p.lending.entity.Biao
import com.p2p.lending.service.BiaoService
import com.p2p.lending.util.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("biao")
class BiaoController {
    @Autowired
    private val biaoService: BiaoService? = null

    @RequestMapping("list")
    fun list(model: Model, @RequestParam(value = "currpage", required = false) currpage: String?): String {

        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数

        val biao = Biao()

        val list = biaoService!!.findList(BeanUtils.toMap(biao)!!)
        totalrow = list.size// 获取总行数
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        totalpage = (totalrow + pagerow - 1) / pagerow
        if (currpages < 1) {
            currpages = 1
        }
        if (currpages > totalpage) {
            if (totalpage < 1) {
                totalpage = 1
            }
            currpages = totalpage
        }
        val startPage = (currpages - 1) * pagerow
        biao.startPage = startPage
        biao.pageSize = 5

        val list2 = biaoService.findList(BeanUtils.toMap(biao)!!)

        model.addAttribute("list", list2)
        model.addAttribute("listNo", list)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)
        return baseDir + "bk_biao_list"
    }

    @RequestMapping(value = ["save"])
    fun save(biao: Biao): String {
        if (biao.id == null) {
            biaoService!!.create(biao)
        } else {
            biaoService!!.update(biao)
        }
        return "redirect:list.do"
    }

    @RequestMapping("input")
    fun input(params: Biao, model: Model): String {
        val biao: Biao
        if (params.id == null) {
            biao = Biao()
        } else {
            biao = biaoService!![params.id]
        }
        model.addAttribute("domain", biao)
        return baseDir + "bk_input_biao"
    }

    @RequestMapping("delete")
    fun delete(@RequestParam(value = "id", required = true) bid: String): String {
        biaoService!!.delete(Integer.parseInt(bid))
        return "redirect:list.do"
    }

    companion object {
        internal val baseDir = "WEB-INF/view/"
    }
}
