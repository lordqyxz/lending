package com.p2p.lending.controller

import com.p2p.lending.entity.Biao
import com.p2p.lending.entity.Product
import com.p2p.lending.service.BiaoService
import com.p2p.lending.service.ProductService
import com.p2p.lending.service.UsersService
import com.p2p.lending.util.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Controller
@RequestMapping("elseset")
class ElseSettingController {
    private val str = "WEB-INF/view/"

    @Autowired
    private val productService: ProductService? = null
    @Autowired
    private val usersService: UsersService? = null
    @Autowired
    private val biaoService: BiaoService? = null

    @RequestMapping("productAll")
    fun productAll(model: Model, @RequestParam(value = "currpage", required = false) currpage: String?): String {

        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数

        val product = Product()
        val biao = Biao()
        val list = productService!!.findList(BeanUtils.toMap(product)!!)

        totalrow = list.size// 获取总行数
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
        val startPage = (currpages - 1) * pagerow
        product.startPage = startPage
        product.pageSize = 5
        val list2 = productService.findList(BeanUtils.toMap(product)!!)
        val biaos = biaoService!!.findList(BeanUtils.toMap(biao)!!)
        productService.updateProgres(list2)
        val users = usersService!!.userList()
        model.addAttribute("users", users)
        model.addAttribute("biaos", biaos)
        model.addAttribute("list", list2)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)

        return str + "updatedeadlines"
    }

    @RequestMapping("updateProduct")
    @ResponseBody
    fun updateProduct(id: Int?, pincome: String, ptime: String, pcount: String): String {
        var code = "200"
        //System.out.println("项目id=="+id+">>>> 利率pincome=="+pincome+">>>> 筹款结束时间ptime=="+ptime+">>>> 还款时间pcount=="+pcount);
        val format = SimpleDateFormat("yyyy-MM-dd")
        var date1: Date? = null
        var date2: Date? = null
        try {
            date1 = format.parse(ptime)
            date2 = format.parse(pcount)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val p = Product()
        p.id = id
        p.pincome = pincome
        p.ptime = date1
        p.pcount = date2
        val updatecode = productService!!.setRateAndDeadline(p)
        if (updatecode <= 0) {
            code = "400"
        }
        return code
    }

}
