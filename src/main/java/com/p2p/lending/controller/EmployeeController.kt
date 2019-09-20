package com.p2p.lending.controller

import com.p2p.lending.entity.Employee
import com.p2p.lending.entity.Limi
import com.p2p.lending.service.DeptService
import com.p2p.lending.service.EmployeeService
import com.p2p.lending.service.LimitService
import com.p2p.lending.util.DateUtil
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpSession
import kotlin.collections.arrayListOf as arrayListOfKotlin

@Controller
@RequestMapping("employee")
class EmployeeController {
    private val str = "WEB-INF/view/"
    private val log = Logger.getLogger(this.javaClass)
    @Autowired
    private val employeeService: EmployeeService? = null
    @Autowired
    private val deptService: DeptService? = null
    @Autowired
    private val limitService: LimitService? = null

    @RequestMapping("list")
    fun list(model: Model): String {
        val emplist = employeeService!!.findlist()
        model.addAttribute("emp", emplist)
        // 查询部门
        val deptlist = deptService!!.findall()
        model.addAttribute("depts", deptlist)
        return str + "bk_emplist"
    }

    @RequestMapping("insert")
    fun insert(
            emp: Employee,
            @RequestParam(value = "ename", required = false) ename: String,
            @RequestParam(value = "epassword", required = false) epassword: String): String {

        emp.ename = ename
        emp.epassword = epassword
        employeeService!!.insert(emp)
        return "bglogin"
    }

    @RequestMapping("bglogin")
    fun bglogin(
            model: Model,
            session: HttpSession,
            @RequestParam(value = "ename", required = false) ename: String,
            @RequestParam(value = "epassword", required = false) epassword: String): String {
        val status: String
        println("$ename------>>>>--->>>>>>>>>$epassword")
        val emp = employeeService!!.empLogin(ename, epassword)

        if (emp == null || emp.equals("")) {
            status = "账号或密码有误"
            println("账号或密码有误")
            model.addAttribute("status", status)
            return "bk_login"
        } else {
            // 登录成功
            println("登录成功")
            //查询权限
            val limitlist = limitService!!.limitByeid(emp.eid!!)
            val list2 = arrayListOfKotlin<String?>();
            for (i in limitlist.indices) {
                val limi = limitlist[i] as Limi
                list2.add(limi.mid)
            }
            //将list 返回到页面
            session.setAttribute("listlimit", list2)
            model.addAttribute("emp", emp)
            // 将登入信息保存到session中
            session.setAttribute("globalemp", emp)
            return "redirect:../log/tologin.do"
        }

    }

    @RequestMapping("add")
    fun add(model: Model, emp: Employee,
            @RequestParam(value = "ebirths") ebirths: String,
            @RequestParam(value = "etimes") etimes: String): String {
        // 设置性别
        val esex = emp.esex
        if (esex == "0") {
            emp.esex = "女"
        } else {
            emp.esex = "男"
        }
        emp.ebirth = DateUtil.strchangedate(ebirths)
        emp.etime = DateUtil.strchangedate(etimes)
        employeeService!!.add(emp)
        return "redirect:/employee/list.do"
    }

    @RequestMapping("upd")
    fun upd(emp: Employee,
            @RequestParam(value = "ebirths") ebirths: String,
            @RequestParam(value = "etimes") etimes: String): String {
        // 设置性别
        val esex = emp.esex
        if (esex == "0") {
            emp.esex = "女"
        } else {
            emp.esex = "男"
        }

        emp.ebirth = DateUtil.strchangedate(ebirths)
        emp.etime = DateUtil.strchangedate(etimes)
        println(emp.eid.toString() + "---" + emp.esex)
        employeeService!!.upd(emp)
        return "redirect:/employee/list.do"
    }

    @RequestMapping("toadd")
    fun inserts(model: Model): String {
        val deptlist = deptService!!.findall()
        model.addAttribute("dept", deptlist)
        return str + "bk_empadd"
    }

    @RequestMapping("del")
    fun del(@RequestParam(value = "eid", required = false) eid: Int?): String {

        employeeService!!.del(eid)

        return "redirect:/employee/list.do"
    }

    @RequestMapping("toupd")
    fun toupd(
            @RequestParam(value = "eid", required = false) eid: Int?,
            model: Model): String {
        // 查询结果
        val ee = employeeService!!.toupd(eid)
        model.addAttribute("ee", ee)
        // 查询部门
        val deptlist = deptService!!.findall()
        model.addAttribute("dept", deptlist)
        return str + "bk_empupd"
    }

    @RequestMapping("selectlike")
    fun selectlike(model: Model,
                   @RequestParam(value = "ename", required = false) ename: String): String {

        val emplist = employeeService!!.selectlike(ename)
        model.addAttribute("emp", emplist)

        // 查询部门
        val deptlist = deptService!!.findall()
        model.addAttribute("depts", deptlist)

        return str + "bk_emplist"
    }

    //注册验证用户名已经存在
    @RequestMapping("findByName")
    @ResponseBody
    fun findByName(@RequestParam(value = "ename", required = false) ename: String): Int {
        val ee = employeeService!!.findByName(ename)
        return if (ee == null) {
            //查询没结果
            2
        } else {
            1
        }
    }


}
