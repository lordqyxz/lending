package com.p2p.lending.controller

import com.p2p.lending.service.PoundageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import java.util.HashMap

@Controller
@RequestMapping("pa")
class PoundageController {
    internal var str = "WEB-INF/view/"
    @Autowired
    private val ps: PoundageService? = null


    @RequestMapping("poundage")
    fun bankcard(m: Model, @RequestParam(value = "currpage", required = false) currpage: String, @RequestParam(value = "uname", required = false) uname: String, @RequestParam(value = "zname", required = false) zname: String, @RequestParam(value = "yyy", required = false) yyy: String, @RequestParam(value = "yyyy", required = false) yyyy: String, req: HttpServletRequest): String {
        val session = req.session
        session.setAttribute("uname", uname)
        session.setAttribute("zname", zname)
        session.setAttribute("yyy", yyy)
        session.setAttribute("yyyy", yyyy)
        val findmap = HashMap<String, Any>()
        findmap["uname"] = uname
        findmap["yyy"] = yyy
        findmap["yyyy"] = yyyy
        findmap["zname"] = zname
        val bc = ps!!.selectpa(currpage, findmap)
        m.addAttribute("lpa", bc["lpa"])
        m.addAttribute("pagerow", bc["pagerow"])
        m.addAttribute("currpages", bc["currpages"])
        m.addAttribute("totalpage", bc["totalpage"])
        m.addAttribute("totalrow", bc["totalrow"])
        return str + "Poundagelist"
    }
}
