package com.p2p.lending.controller

import com.p2p.lending.entity.Certification
import com.p2p.lending.entity.Users
import com.p2p.lending.service.CertificationService
import com.p2p.lending.service.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*
import javax.annotation.Resource
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("users")
class UsersController {
    internal var str = "WEB-INF/view/"

    @Autowired
    private val usersService: UsersService? = null
    @Autowired
    @Resource
    private val Certificat: CertificationService? = null

    @RequestMapping("list")
    fun list(model: Model, users: Users,
             @RequestParam(value = "currpage", required = false) currpage: String?,
             @RequestParam(value = "unickname", required = false) unickname: String?): String {
        // 查询所有
        val pagerow = 6// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数
        val parameters = HashMap<String, Any>()
        totalrow = usersService!!.userList().size//获取总行数

        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        totalpage = (totalrow + pagerow - 1) / pagerow
        if (currpages < 1) {
            currpages = 1
        }
        if (currpages > totalpage) {
            currpages = totalpage
        }
        val candp = (currpages - 1) * pagerow
        parameters["pandc"] = pagerow
        parameters["candp"] = candp
        var ulist = usersService.queryUserslimits(parameters)
        if (unickname != null) {
            ulist = usersService.queryUserslimits(unickname)
            model.addAttribute("sta", 1)
        }
        if (ulist.size == 0) {
            model.addAttribute("stas", 1)
        } else {
            model.addAttribute("stas", 0)
        }
        model.addAttribute("ulist", ulist)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpage", currpages)
        model.addAttribute("totalpage", totalpage)
        return str + "bk_userslist"
    }

    // 注册新用户 start
    @RequestMapping("insert")
    fun insert(users: Users, model: Model,
               @RequestParam(value = "unickname", required = false) unickname: String,
               @RequestParam(value = "upassword", required = false) upassword: String,
               @RequestParam(value = "uphonenumber", required = false) uphonenumber: String): String {
        users.unickname = unickname
        users.upassword = upassword
        users.uphonenumber = uphonenumber
        // 将数据添加到数据库
        usersService!!.insert(users)//users.getUid()
        val cer = Certification()
        cer.cserial = users.uid!!.toString() + ""
        cer.cbalance = "0"
        cer.cusername = "1"
        cer.crealname = "1"
        cer.cfreeze = "0"
        cer.cdue = "0"
        cer.cpaid = "0"
        cer.ctotalmoney = "0"
        Certificat!!.insert(cer)
        model.addAttribute("unickname", unickname)
        // 返回到注册成功界面
        return "registerSuccess"
    }
    // 注册新用户 end

    // 登录 start
    @RequestMapping("login")
    fun login(model: Model, session: HttpSession,
              @RequestParam(value = "unickname", required = false) unickname: String,
              @RequestParam(value = "upassword", required = false) upassword: String): String {
        val status: String
        // 根据账号查询 是否为null进行判断
        println("$unickname-----------------$upassword")
        val user = usersService!!.byNameFindUsers(unickname, upassword)
        if (user == null) {
            // 登录失败
            status = "账号或密码有误"
            model.addAttribute("status", status)
            return "login"
        } else {
            // 登录成功
            model.addAttribute("users", user)
            // 将登入信息保存到session中
            session.setAttribute("globaluser", user)
            return "index"
        }
    }
    // 登录 end

    // 退出start
    @RequestMapping("exit")
    fun exit(session: HttpSession): String {
        val em = session.attributeNames
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement().toString())
        }
        return "index"
    }

    // 退出end
    @RequestMapping("findByName")
    @ResponseBody
    fun findByName(@RequestParam(value = "unickname", required = false) unickname: String): Int {
        println("unickname$unickname")
        val ulist = usersService!!.queryUserslimits(unickname)
        return if (ulist.size > 0) {
            2
        } else {
            1
        }
    }

}
