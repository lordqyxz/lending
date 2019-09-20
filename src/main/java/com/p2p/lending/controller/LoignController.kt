package com.p2p.lending.controller

import com.p2p.lending.service.BidService
import com.p2p.lending.service.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import javax.annotation.Resource

/**
 * @author 周旗 2017-2-23 10:19:37 网站消息通知控制层
 */
@Controller
@RequestMapping("log")
class LoignController {
    @Resource
    private val bidService: BidService? = null
    @Autowired
    private val usersService: UsersService? = null

    @RequestMapping("tologin")
    fun tologin(model: Model): String {
        val i = bidService!!.tosize()
        val j = bidService.tosizew()
        //查询所有新用户

        model.addAttribute("tos", i)
        model.addAttribute("tow", j)
        model.addAttribute("tou", usersService!!.userList().size)
        model.addAttribute("tob", bidService.tosizeb())

        return "WEB-INF/view/bk_index"
    }

}
