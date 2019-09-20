package com.p2p.lending.controller

import com.p2p.lending.entity.Dept
import com.p2p.lending.service.DeptService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("dept")
class DeptController {
    private val log = Logger.getLogger(this.javaClass)

    @Autowired
    private val deptService: DeptService? = null

    @RequestMapping("findall")
    fun findall(model: Model): String {
        val deptlist = deptService!!.findall()
        model.addAttribute("deptlist", deptlist)
        return "WEB-INF/view/bk_deptlist"
    }

    @RequestMapping("insert")
    fun insert(dept: Dept, model: Model, @RequestParam(value = "dname", required = false) dname: String,
               @RequestParam(value = "describe", required = false) describe: String): String {
        dept.dname = dname
        dept.describes = describe
        deptService!!.insert(dept)
        return "redirect:/dept/findall.do"
    }

    @RequestMapping("toadd")
    fun inserts(): String {
        return "WEB-INF/view/bk_deptadd"
    }

    @RequestMapping("del")
    fun del(@RequestParam(value = "did", required = false) did: Int?): String {

        deptService!!.del(did)

        return "redirect:/dept/findall.do"
    }

    @RequestMapping("toupd")
    fun toupd(@RequestParam(value = "did", required = false) did: Int?, model: Model): String {

        val dept = deptService!!.findbyid(did)

        model.addAttribute("dept", dept)
        return "WEB-INF/view/bk_deptupd"
    }

    @RequestMapping("upd")
    fun upd(@RequestParam(value = "did", required = false) did: Int?,
            @RequestParam(value = "dname", required = false) dname: String,
            @RequestParam(value = "describes", required = false) describes: String): String {
        val dept = deptService!!.findbyid(did)
        dept.describes = describes
        dept.dname = dname
        deptService.upd(dept)
        return "redirect:/dept/findall.do"
    }

}
