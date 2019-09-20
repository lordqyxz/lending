package com.p2p.lending.controller

import com.p2p.lending.entity.Notice
import com.p2p.lending.service.NoticeService
import com.p2p.lending.service.UsersService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

import javax.annotation.Resource
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.File
import java.io.IOException

/**
 * @author 周旗 2017-2-23 10:19:37 网站消息通知控制层
 */
@Controller
@RequestMapping("notice")
class NoticeController {
    @Resource
    private val noticeService: NoticeService? = null
    @Resource
    private val uService: UsersService? = null

    // @RequestMapping("tohoutai")
    // public String tohoutai(){
    //
    // return "WEB-INF/view/indexs";
    // }

    // 去后台添加页面
    @RequestMapping("toadd")
    fun tohoutai(): String {

        return "WEB-INF/view/noticeadd"

    }

    // 去添加首页图片
    @RequestMapping("addtupian")
    fun addtupian(): String {

        return "WEB-INF/view/noticeaddtupian"

    }

    //sss
    // 查询首页图片
    @RequestMapping("toaddlisttupian")
    fun toaddlisttupian(model: Model, ids: String): String {
        model.addAttribute("list", noticeService!!.noticelist(ids))
        return "WEB-INF/view/noticeaddlisttupian"
    }

    // 查询通知
    @RequestMapping("toaddlist")
    fun toaddlist(): String {

        return "WEB-INF/view/noticeaddlist"
    }


    @RequestMapping("nottop")
    fun nottop(request: HttpServletRequest, @RequestParam(value = "ids") ids: Int?, @RequestParam(value = "isd") isd: Int?): String {
        noticeService!!.noticshiji(ids)
        jiazai(request)

        return if (6 == isd) {
            "redirect:toaddlisttupian.do?ids=6"
        } else "redirect:notlists.do?ids=" + isd!!


    }

    // 前台(list页面)
    @RequestMapping("notlist")
    fun notlist(model: Model,
                @RequestParam(value = "ids", required = false) ids: String): String {
        model.addAttribute("list", noticeService!!.noticelist(ids))
        model.addAttribute("idss", ids + "")
        if ("3" == ids) {
            return "informgltd"
        } else if ("4" == ids) {
            return "informhzhb"
        } else if ("5" == ids) {
            return "informtdfc"
        }
        return "inform"
    }

    // 后台查询
    @RequestMapping("notlists")
    // public String notlists(Model model) {
    fun notlists(request: HttpServletRequest, model: Model, ids: String): String {
        val list = noticeService!!.noticelist(ids)
        model.addAttribute("list", list)
        jiazai(request)
        return "WEB-INF/view/noticeaddlist"
    }

    // 查询单条详情
    @RequestMapping("notget")
    fun notget(model: Model, @RequestParam(value = "ids") ids: Int?): String {
        model.addAttribute("notice", noticeService!!.noticeget(ids))
        return "informsel"
    }

    @RequestMapping("notdel")
    fun notdel(request: HttpServletRequest, @RequestParam(value = "ids") ids: Int?, @RequestParam(value = "isd") isd: Int?): String {
        noticeService!!.noticedel(ids)
        jiazai(request)

        return if (6 == isd) {
            "redirect:toaddlisttupian.do?ids=6"
        } else "redirect:notlists.do?ids=" + isd!!


    }

    @RequestMapping("noticeupds")
    fun noticeupds(@RequestParam(value = "ids") ids: Int?,
                   model: Model): String {
        val notice = noticeService!!.noticeget(ids)
        model.addAttribute("not", notice)
        return "WEB-INF/view/noticeupdate"
    }

    // 后台添加
    @RequestMapping("notadd")
    fun notadd(
            request: HttpServletRequest,
            response: HttpServletResponse,
            @RequestParam(value = "ufile", required = false) file: MultipartFile,
            model: Model, notice: Notice): String {
        val path = request.session.servletContext
                .getRealPath("file")// 获得上传的路径
        val fileName = file.originalFilename// 获得上传的文件名
        val targetFile = File(path, fileName!!)// 创建上传到服务器的文件对象
        try {
            file.transferTo(targetFile)// 文件转储
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val imgUrl = request.contextPath + "/file/" + fileName

        notice.noticepicture = imgUrl
        noticeService!!.noticeadd(notice)

        jiazai(request)
        //Sos
        return if ("6" == notice.noticetype) {
            "redirect:toaddlisttupian.do?ids=" + notice.noticetype!!
        } else "redirect:notlists.do?ids=" + notice.noticetype!!
    }

    @RequestMapping("notupd")
    fun notupd(request: HttpServletRequest,
               response: HttpServletResponse,
               @RequestParam(value = "ufile", required = true) file: MultipartFile,
               model: Model, notice: Notice): String {

        if (file.size != 0L) {
            val path = request.session.servletContext
                    .getRealPath("file")// 获得上传的路径
            val fileName = file.originalFilename// 获得上传的文件名
            val targetFile = File(path, fileName!!)// 创建上传到服务器的文件对象
            try {
                file.transferTo(targetFile)// 文件转储
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val imgUrl = request.contextPath + "/file/" + fileName
            notice.noticepicture = imgUrl

        }
        noticeService!!.noticeupds(notice)
        jiazai(request)

        return if ("6" == notice.noticetype) {
            "redirect:toaddlisttupian.do?ids=" + notice.noticetype!!
        } else "redirect:notlists.do?ids=" + notice.noticetype!!
    }

    // 去修改
    @RequestMapping("sgetno")
    fun getno(model: Model, ids: Int?): String {
        //		notgets(model, ids);
        val notice = noticeService!!.noticeget(ids)
        model.addAttribute("nots", notice)
        return "WEB-INF/view/noticeupdate"
    }

    @RequestMapping("noticetop5")
    fun noticetop5(request: HttpServletRequest): String {
        jiazai(request)
        return "redirect:/invest/recommendShow.do"
    }

    // 重新加载top5
    private fun jiazai(request: HttpServletRequest) {
        val list = noticeService!!.noticetop5()
        val lists = noticeService.noticetop5meiti()
        val listss = noticeService.noticetop5sy()

        println(">>>>>>>>>>>>>>>>>>>>>>>top5")
        val context = request.session.servletContext
        context.setAttribute("listss", list)
        context.setAttribute("meiti", lists)
        context.setAttribute("sy", listss)
        context.setAttribute("size", uService!!.userList().size)
    }

    private fun notgets(model: Model, ids: Int?) {
        val notice = noticeService!!.noticeget(ids)
        model.addAttribute("nots", notice)
    }

}
