package com.p2p.lending.controller

import com.p2p.lending.entity.Limi
import com.p2p.lending.service.DeptService
import com.p2p.lending.service.EmployeeService
import com.p2p.lending.service.LimitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import kotlin.collections.arrayListOf as arrayListOfKotlin

@Controller
@RequestMapping("limit")
class LimitController {

    @Autowired
    private val limitService: LimitService? = null
    @Autowired
    private val employeeService: EmployeeService? = null
    @Autowired
    private val deptService: DeptService? = null


    @RequestMapping("findlist")
    fun findlist(model: Model): String {
        val emplist = employeeService!!.findlist()
        model.addAttribute("emp", emplist)
        // 查询部门
        val deptlist = deptService!!.findall()
        model.addAttribute("depts", deptlist)
        return "/WEB-INF/view/limit"
    }


    @RequestMapping("toupd")
    fun toupd(@RequestParam(value = "eid", required = false) eid: Int?,
              requeset: HttpServletRequest,
              model: Model): String {
        //根据ID查询权限
        val list = limitService!!.limitByeid(eid!!)
        val list2 = arrayListOfKotlin<String?>()
        for (i in list.indices) {
            val limi = list[i] as Limi
            list2.add(limi.mid)
        }
        //将list 返回到页面
        requeset.setAttribute("listss", list2)
        println(list.size)
        model.addAttribute("eid", eid)
        return "/WEB-INF/view/limitupd"
    }

    @RequestMapping("upd")
    fun upd(@RequestParam(value = "eid", required = false) eid: Int?,
            @RequestParam(value = "inp", required = false) rools: Array<String>?,
            model: Model): String {
        //先清空原有的数据（删除）--根据ID查询  很多数据
        limitService!!.limitdel(eid)
        //System.out.println(eid+"------------");
        //System.out.println(liss.size()+"--------------");

        if (rools == null || rools.equals("")) {
            println("rools大小为0")
        } else {
            for (i in rools.indices) {
                val limit = Limi()
                limit.mid = rools[i]
                limit.eid = eid
                //执行添加的方法
                limitService.limitadd(limit)
            }
        }
        return "redirect:/limit/findlist.do"
    }


}
