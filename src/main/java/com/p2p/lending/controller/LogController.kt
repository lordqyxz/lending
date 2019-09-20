package com.p2p.lending.controller

import com.p2p.lending.entity.Log
import com.p2p.lending.service.LoggerService
import com.p2p.lending.util.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/log")
class LogController {


    @Autowired
    private val service: LoggerService? = null

    @RequestMapping("/list")
    fun list(): String {

        println("查询日志   》》》》》》》》》》》》")

        val log = Log()

        val page = service!!.findList(BeanUtils.toMap(log)!!)

        println(" 222222222222      $page")

        return "/back/test"

    }
}
