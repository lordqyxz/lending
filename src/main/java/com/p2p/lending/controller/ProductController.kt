package com.p2p.lending.controller

import com.p2p.lending.entity.Biao
import com.p2p.lending.entity.Details
import com.p2p.lending.entity.InvestInfo
import com.p2p.lending.entity.Product
import com.p2p.lending.service.*
import com.p2p.lending.util.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("product")
class ProductController {
    @Resource
    internal var InvestService: InvestService? = null
    @Autowired
    private val usersService: UsersService? = null
    @Autowired
    private val biaoService: BiaoService? = null
    @Resource
    private val detailsService: DetailsService? = null
    @Resource
    private val service: ProductService? = null

    @RequestMapping("input")
    fun addpro(model: Model, params: Product): String {
        val product: Product
        if (params.id == null) {
            product = Product()
        } else {
            product = service!![params.id]
        }
        model.addAttribute("domain", product)
        val list3 = biaoService!!.findList(BeanUtils.toMap(Biao())!!)
        model.addAttribute("blist", list3)
        return str + "bk_input_pro"
    }

    @RequestMapping("list")
    fun list(model: Model, @RequestParam(value = "currpage", required = false) currpage: String?,
             @RequestParam(value = "status", required = false) status: String?): String {
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数
        val product = Product()
        val list = service!!.findList(BeanUtils.toMap(product)!!)
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
        product.startPage = startPage
        product.pageSize = pagerow
        if (status != null && status == "ing") {
            product.pstate = "1"
        }
        if (status != null && status == "over") {
            product.pstate = "2"
        }

        val list2 = service.findList(BeanUtils.toMap(product)!!)
        // 更新进度
        service.updateProgres(list2)
        // 更新状态
        service.updateStatus(list2)
        model.addAttribute("list", list2)

        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)

        return str + "bk_list_pro"
    }

    @RequestMapping("biaolist")
    fun biaolist(model: Model, @RequestParam(value = "currpage", required = false) currpage: String?,
                 @RequestParam(value = "bid", required = true) bid: String): String {
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数
        val ii = InvestInfo()
        ii.brrowid = Integer.parseInt(bid)
        val list = InvestService!!.getDtail(BeanUtils.toMap(ii)!!)
        val ulist = usersService!!.userList()
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
        ii.startPage = startPage
        ii.pageSize = pagerow

        val list2 = InvestService!!.getDtail(BeanUtils.toMap(ii)!!)
        println(list2.size)
        // 更新进度
        // service.updateProgres(list2);
        // 更新状态
        // service.updateStatus(list2);
        model.addAttribute("us", ulist)
        model.addAttribute("bid", bid)
        model.addAttribute("list", list2)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)

        return str + "bk_list_biaos"
    }

    @RequestMapping(value = ["save"], method = [RequestMethod.POST])
    fun save(request: HttpServletRequest, product: Product,
             @RequestParam(value = "pictures", required = false) multipartFile: MultipartFile, model: Model): String {
        if (product.ppublishtime == null || product.ppublishtime!!.equals("")) {
            product.ppublishtime = Date()
        }
        if (product.pcount == null || product.pcount!!.equals("")) {
            product.pcount = Date()
        }
        if (product.ptime == null || product.ptime!!.equals("")) {
            product.ptime = Date()
        }
        val fileName = multipartFile.originalFilename
        // 获取文件夹路径
        val Path = request.session.servletContext.getRealPath("cover")// 获得上传的路径
        val file = File(Path, fileName!!)

        try {
            multipartFile.transferTo(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 设置文件保存路径
        val imgUrl = request.contextPath + "/cover/" + fileName
        product.picture = imgUrl
        if (product.id == null) {
            service!!.create(product)
            val id = product.id!!

            println("id==   $id")
            request.setAttribute("id", id)
        } else {
            service!!.update(product)
        }
        model.addAttribute("domain", product)

        return str + "bk_input_detail"
    }

    // 添加详情
    @RequestMapping("todetail")
    fun todetail(model: Model, session: HttpSession, product: Product): String {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

        if (product.ptype == null || product.ptype!!.equals("")
                || product.ptype!!.equals("请选择")  ) {//注意数据库数据格式

            product.ptype = null
        }
        val details = product.details
        details!!.pid = product.id
        val details2 = Details()

        //把对象拷贝到新的对象
        org.springframework.beans.BeanUtils.copyProperties(details, details2)
        detailsService!!.create(details2)
        model.addAttribute("domain", product)
        return str + "bk_input_detail"
    }

    @RequestMapping("delete")
    fun delete(@RequestParam("pid") pid: String): String {
        val id = Integer.parseInt(pid)
        service!!.delete(id)
        return "redirect:/product/list.do"
    }

    @RequestMapping("detail")
    fun detail(model: Model, @RequestParam(value = "pid", required = true) id: String): String {
        val info = InvestInfo()
        info.brrowid = Integer.parseInt(id)
        val list = InvestService!!.getDtail(BeanUtils.toMap(info)!!)
        model.addAttribute("list", list)
        return str + "bk_pro_detail"
    }

    companion object {

        internal val str = "WEB-INF/view/"
    }

}
