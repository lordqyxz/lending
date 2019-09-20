package com.p2p.lending.controller

import com.p2p.lending.entity.Biao
import com.p2p.lending.entity.Borrowcord
import com.p2p.lending.entity.Borrowmoney
import com.p2p.lending.service.BiaoService
import com.p2p.lending.service.BorrowcordService
import com.p2p.lending.service.BorrowmoneyService
import com.p2p.lending.util.BeanUtils
import org.apache.ibatis.annotations.Param
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("brower")
class BorrowmoneyController {
    @Autowired
    var biaoService: BiaoService? = null
    @Autowired
    private val borrowmoneyService: BorrowmoneyService? = null
    @Autowired
    private val borrowcordService: BorrowcordService? = null

    @RequestMapping("qurey")
    fun allMoney(request: HttpServletRequest, borrowmoney: Borrowmoney, model: Model,
                 @RequestParam(value = "currpage", required = false) currpage: String?): String {
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数
        val borrowmoney1 = Borrowmoney()
        borrowmoney1.btype = borrowmoney.btype
        borrowmoney1.bstate = borrowmoney.bstate
        borrowmoney1.busername = borrowmoney.busername

        if (borrowmoney.btype == null || borrowmoney.btype == ""
                || borrowmoney.btype == "请选择") {

            borrowmoney1.btype = null
        }
        if (borrowmoney.bstate == null || borrowmoney.equals("") || borrowmoney.bstate == "请选择") {

            borrowmoney1.bstate = null
        }
        val list = borrowmoneyService!!.findList(BeanUtils.toMap(borrowmoney1)!!)
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
        borrowmoney1.startPage = startPage
        borrowmoney1.pageSize = pagerow
        val list2 = borrowmoneyService.findList(BeanUtils.toMap(borrowmoney1)!!)
        model.addAttribute("list", list2)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)
        val bList = biaoService!!.findList(BeanUtils.toMap(Biao())!!)

        model.addAttribute("page", list2)
        model.addAttribute("bList", bList)

        return str + "bk_moneylist"
    }

    @RequestMapping("audit")
    fun audit(model: Model, @RequestParam(value = "currpage", required = false) currpage: String?,
              @RequestParam(value = "id", required = false) id: String,
              @RequestParam(value = "status", required = true) status: String): String {
        val borrowmoney1 = Borrowmoney()
        // 通过
        if (status == "1") {
            borrowmoney1.bstate = "1"
        }
        // 不通过
        if (status == "0") {
            borrowmoney1.bstate = "2"
        }
        borrowmoney1.id = Integer.parseInt(id)
        borrowmoneyService!!.update(borrowmoney1)

        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数
        borrowmoney1.bstate = "0"
        val list = borrowmoneyService.findList(BeanUtils.toMap(borrowmoney1)!!)
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
        borrowmoney1.startPage = startPage
        borrowmoney1.pageSize = pagerow
        val list2 = borrowmoneyService.findList(BeanUtils.toMap(borrowmoney1)!!)
        model.addAttribute("page", list2)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)

        return str + "bk_money_check"
    }

    @RequestMapping(value = ["check"])
    fun check(borrowmoney: Borrowmoney, model: Model,
              @RequestParam(value = "currpage", required = false) currpage: String?): String {
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数
        val borrowmoney1 = Borrowmoney()
        borrowmoney1.bstate = "0"
        val list = borrowmoneyService!!.findList(BeanUtils.toMap(borrowmoney1)!!)
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
        borrowmoney1.startPage = startPage
        borrowmoney1.pageSize = pagerow
        val list2 = borrowmoneyService.findList(BeanUtils.toMap(borrowmoney1)!!)

        model.addAttribute("page", list2)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)

        return str + "bk_money_check"

    }

    @RequestMapping("find")
    fun find(model: Model, @Param(value = "id") id: String?): String {
        var id = id
        if (id == null || id == "") {
            id = 1.toString() + ""
        }
        val ia = Integer.parseInt(id)
        val mBorrowmoney = borrowmoneyService!![ia]
        model.addAttribute("domain", mBorrowmoney)
        return str + "bk_money_detail"
    }

    // 周旗 2017年3月3日10:44:13__________json添加还款(前台)
    @RequestMapping("toaddborr")
    @ResponseBody
    fun toadd(borrowmoney: Borrowmoney): String {
        borrowmoneyService!!.toaddborr(borrowmoney)
        return ""
    }

    // 周旗 __________还款(查询所有需要还款的还款)
    @RequestMapping("tohk")
    fun updhuankuan(model: Model): String {
        println(borrowmoneyService!!.updhuankuan().size.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        model.addAttribute("list", borrowmoneyService.updhuankuan())

        return str + "bk_huankuanlist"
    }

    // 周旗 __________去查看还款详情页面
    @RequestMapping("tohuankuanupd")
    fun tohuankuan(model: Model, @RequestParam(value = "id") ids: Int?): String {
        val borr = borrowmoneyService!![ids]
        model.addAttribute("borr", borr)
        model.addAttribute("list", borrowcordService!!.selborr(ids))

        return str + "bk_huankuanupdeta"
    }

    // 周旗 __________修改还款状态
    @RequestMapping("tohuankuanupds")
    fun tohuankuanupd(model: Model, @RequestParam(value = "ids") ids: Int?,
                      @RequestParam(value = "id") id: Int?): String {
        println(">>>>>>>>>>>>>>>>>>>>>>>>>修改还款状态")
        borrowcordService!!.updborr(ids)
        return "redirect:tohuankuanupd.do?id=" + id!!
    }

    // 周旗 __________json
    @RequestMapping("tohuankuanupdison")
    @ResponseBody
    fun tohuankuanjson(@RequestParam(value = "id") ids: Int?): List<Borrowcord> {
        return borrowcordService!!.selborr(ids)
    }

    // 周旗 点击同意时进入借款信息确认见面
    @RequestMapping("borqr")
    fun borqr(model: Model, @RequestParam(value = "ids") ids: Int?): String {
        val borro = borrowmoneyService!!.borrowget(ids)
        model.addAttribute("borr", borro)
        return str + "bk_huankuanget"
    }

    // 周旗 点击同意时进入借款信息确认见面 (去修改)
    @RequestMapping("borxg")
    fun borxg(model: Model, borrowmoney: Borrowmoney): String {
        //修改状态
        borrowmoney.bstate = "1"
        borrowmoneyService!!.update(borrowmoney)
        //处理还款记录表
        borrowcordService!!.borradd(borrowmoney.btimelimit!!, borrowmoney.id, borrowmoney.bserial!!)

        return "redirect:check.do"
    }

    // hjy
    @RequestMapping("hjyList")
    fun hjyList(m: Model, @RequestParam(value = "currpage", required = false) currpage: String): String {
        val wmap = borrowmoneyService!!.selecthjy(currpage)
        val llist = wmap["llist"] as List<Borrowmoney>
        m.addAttribute("pagerow", wmap["pagerow"])
        m.addAttribute("currpages", wmap["currpages"])
        m.addAttribute("wdlist", llist)
        m.addAttribute("totalpage", wmap["totalpage"])
        m.addAttribute("totalrow", wmap["totalrow"])
        return str + "Borrowmoneylist"
    }

    // hjy
    @RequestMapping("bajax")
    @ResponseBody
    fun ajax(@RequestParam(value = "id", required = false) id: Int): Borrowmoney {
        println(id)
        println(borrowmoneyService!![id].brelname)
        return borrowmoneyService[id]
    }

    companion object {
        internal val str = "WEB-INF/view/"
    }

}
