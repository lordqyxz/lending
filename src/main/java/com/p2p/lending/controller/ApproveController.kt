package com.p2p.lending.controller

import com.p2p.lending.entity.*
import com.p2p.lending.service.*
import com.p2p.lending.util.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author chenqingshan
 * @Name: ApproveController
 * @Description:认证项设置
 * @Date: 2017-2-20 Time: 20:24
 */
@Controller
@RequestMapping("approve")
class ApproveController {
    internal var str = "WEB-INF/view/"
    @Autowired
    private val approveService: ApproveService? = null

    @Autowired
    private val usersService: UsersService? = null
    @Autowired
    private val employeeService: EmployeeService? = null
    @Autowired
    private val certifrecordService: CertifrecordService? = null
    @Autowired
    private val userauditorService: UserauditorService? = null
    @Autowired
    private val informationService: InformationService? = null
    @Autowired
    private val clapplyforService: ClapplyforService? = null
    @Autowired
    private val creditlimitService: CreditlimitService? = null

    @RequestMapping("traverseApproves")
    private fun traverseApproves(model: Model, ai: Approveitem,
                                 @RequestParam(value = "currpage", required = false) currpage: String?): String {

        val pageRow = 5// 每页5行
        var currPages = 1// 当前页
        var totalPage: Int// 总页数
        var totalRow: Int// 总行数

        val parameters = HashMap<String, Any?>()
        parameters["aiid"] = ai.aiid
        parameters["ainame"] = ai.ainame
        parameters["aitype"] = ai.aitype
        parameters["aistate"] = ai.aistate
        val approvesall = approveService!!.queryApproves(parameters)

        totalRow = approvesall.size// 获取总行数
        if (currpage != null && "" != currpage) {
            currPages = Integer.parseInt(currpage)
        }
        totalPage = (totalRow + pageRow - 1) / pageRow

        if (currPages < 1) {
            currPages = 1
        }
        if (currPages > totalPage) {
            currPages = totalPage
        }

        val candp = (currPages - 1) * pageRow
        parameters["pandc"] = pageRow
        parameters["candp"] = candp
        val approves = approveService.queryApproves(parameters)
        // for (Approveitem approveitem : approves) {
        // System.out.println(approveitem.getApproveitemInfo());
        // }

        model.addAttribute("approveitems", approves)
        model.addAttribute("totalrow", totalRow)
        model.addAttribute("currpages", currPages)
        model.addAttribute("totalpage", totalPage)

        return str + "approvelist"
    }

    @RequestMapping("toaddApprove")
    private fun toaddApprove(): String {

        return str + "approveadd"
    }

    @RequestMapping("addApprove")
    private fun addApprove(ai: Approveitem): ModelAndView {
        // System.out.println("进来了abandonApprove
        // Approveitem=="+ai.getApproveitemInfo());
        val parameters = HashMap<String, Any?>()
        parameters["ainame"] = ai.ainame
        parameters["aitype"] = ai.aitype
        parameters["aistate"] = "1"
        approveService!!.addApproves(parameters)
        return ModelAndView("redirect:traverseApproves.do")
    }

    @RequestMapping("toupdateApprove")
    private fun toupdateApprove(ai: Approveitem, model: Model): String {
        val parameters = HashMap<String, Any?>()
        parameters["aiid"] = ai.aiid
        parameters["ainame"] = ai.ainame
        parameters["aitype"] = ai.aitype
        parameters["aistate"] = ai.aistate
        val approvesall = approveService!!.queryApproves(parameters)
        var approve: Approveitem?
        if (!approvesall.isEmpty()) {
            approve = approvesall[0]
            model.addAttribute("approve", approve)
        }

        return str + "approveupdate"
    }

    @RequestMapping("updateApprove")
    private fun updateApprove(ai: Approveitem): ModelAndView {

        // System.out.println("进来了abandonApprove aiid=="+ai.getAiid()+"
        // aistate=="+ai.getAistate());
        val parameters = HashMap<String, Any?>()
        parameters["aiid"] = ai.aiid
        parameters["ainame"] = ai.ainame
        parameters["aitype"] = ai.aitype
        parameters["aistate"] = ai.aistate
        approveService!!.updateApproves(parameters)
        return ModelAndView("redirect:traverseApproves.do")
    }
    //认证项的管理 头部
    //==============================================================================

    //==============================================================================
    //新用户认真资料 头部
    @RequestMapping("newuserInfoList")
    private fun newuserInfoList(model: Model): String {
        //查询所有新用户
        val users = usersService!!.userList().toMutableList()

        val employees = employeeService!!.findlist()

        val parameters = HashMap<String, Any>()
        //查询出所有用户审核人
        val userauditors = userauditorService!!.queryUseraubitor(parameters)

        //System.out.println("userauditors.size==========================="+userauditors.size());
        //查询出所有用户的审核记录
        val certifrecords = certifrecordService!!.queryCertifrecord(parameters)

        for (userauditor in userauditors) {
            for (users1 in users) {
                if (userauditor.userid === users1.uid) {
                    users.remove(users1)
                    break
                }

            }
        }
        //查询出未分配审核人1的积分，和待审核条数

        var cr: MutableList<Certifrecord>?

        cr = ArrayList()

        for (u in users) {
            val cerrecord = Certifrecord()
            var integral = 0
            var ispass = 0
            for (c in certifrecords) {
                if (u.uid === c.cruserid) {
                    if (c.crintegral != null) {
                        integral += c.crintegral!!
                    } else {
                        integral += 0
                    }

                    if (c.crispass == "1") {
                        ispass += 1
                    }
                }

            }
            //System.out.println("uname="+u.getUnickname()+"  integral: "+integral+" ispass: "+ispass);
            cerrecord.cruserid = u.uid
            cerrecord.crusername = u.unickname
            cerrecord.crintegral = integral
            cerrecord.checkpend = ispass
            cr.add(cerrecord)

        }

        model.addAttribute("cr", cr)
        model.addAttribute("users", users)
        model.addAttribute("employees", employees)
        return str + "anewuserinfolist"
    }


    @RequestMapping("affirmCrauditor")
    @ResponseBody
    fun affirmCrauditor(ua: Userauditor): String {
        var code = "200"
        //System.out.println("进来了affirmCrauditor  auditorid=="+ua.getUauditorid()+"  auditor=="+ua.getUauditor()+" userid=="+ua.getUserid()+"  username=="+ua.getUsername());
        val parameters = HashMap<String, Any?>()
        parameters["userid"] = ua.userid
        parameters["username"] = ua.username
        parameters["uauditorid"] = ua.uauditorid
        parameters["uauditor"] = ua.uauditor
        val addcode = userauditorService!!.addUserauditor(parameters)
        if (addcode <= 0) {
            code = "400"
        } else {
            val parameters1 = HashMap<String, Any?>()
            parameters1["crauditor"] = ua.uauditor
            parameters1["cruserid"] = ua.userid

        }


        return code
    }
    //新用户认证资料 end
    //==============================================================================

    //==============================================================================
    //资料认证
    @RequestMapping("basicInfoApprove")
    private fun basicInfoaudit(model: Model, @RequestParam(value = "currpage", required = false) currpage: String?): String {
        val pagerow = 10// 每页5行
        var currpages = 1// 当前页
        var totalpage: Int// 总页数
        var totalrow: Int// 总行数
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        val user1 = usersService!!.userList()
        val parameters = HashMap<String, Any>()
        val userauditors = userauditorService!!.queryUseraubitor(parameters)
        totalrow = user1.size// 获取总行数

        totalpage = (totalrow + pagerow - 1) / pagerow

        if (currpages < 1) {
            currpages = 1
        }

        if (currpages > totalpage) {
            if (totalpage != 0) {
                currpages = totalpage
            } else {
                currpages = 1
            }
        }
        val candp = (currpages - 1) * pagerow
        parameters["candp"] = candp
        parameters["pandc"] = pagerow
        val users = usersService.queryUser(parameters)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)
        model.addAttribute("users", users)
        model.addAttribute("ua", userauditors)
        return str + "basicinfoList"
    }

    @RequestMapping("infoAuditByuser")
    private fun infoAuditByuser(model: Model, cr: Certifrecord): String {
        //System.out.println("userid==="+cr.getCruserid());
        val parameters = HashMap<String, Any?>()
        val userauditors = userauditorService!!.queryUseraubitor(parameters)
        parameters["cruserid"] = cr.cruserid
        parameters["id"] = cr.cruserid!!
        parameters["craiid"] = cr.craiid
        val certifrecords = certifrecordService!!.queryCertifrecord(parameters)
        val user = informationService!!.query(parameters)

        val approvesall = approveService!!.queryApproves(parameters)
        //System.out.println("user==="+user.getUname()+"  approvesall==="+approvesall.size()+"  certifrecords==="+certifrecords.size());
        model.addAttribute("certrecod", certifrecords)
        model.addAttribute("user", user)
        model.addAttribute("approve", approvesall)
        model.addAttribute("code", approvesall.size)
        model.addAttribute("useraud", userauditors)
        model.addAttribute("craiid", cr.craiid)
        return str + "basicuserapprove"
    }

    @RequestMapping("updateInfoAudit")
    @ResponseBody
    private fun updateInfoAudit(cr: Certifrecord): String {
        var code = "200"
        //"cruserid":userid,"craiid":crtype,"crviewpoint":viewpoint,"crintegral":integral,"crispass":ispass,"crauditor":auditor
        //System.out.println("cruserid=="+cr.getCruserid()+"   craiid=="+cr.getCraiid()+"   crviewpoint=="+cr.getCrviewpoint()+"   crintegral=="+cr.getCrintegral()+"   crispass=="+cr.getCrispass()+"   crauditor=="+cr.getCrauditor());
        val parameters = HashMap<String, Any?>()
        val date = Date()
        parameters["cruserid"] = cr.cruserid
        parameters["craiid"] = cr.craiid
        parameters["crviewpoint"] = cr.crviewpoint
        parameters["crintegral"] = cr.crintegral
        parameters["crispass"] = cr.crispass
        parameters["crauditor"] = cr.crauditor
        parameters["crdate"] = date
        val updateCode = certifrecordService!!.updateCertifrecord(parameters)
        if (updateCode <= 0) {
            code = "400"
        }
        return code
    }

    @RequestMapping("approveStatistics")
    private fun approveStatistics(model: Model, cr: Certifrecord, @RequestParam(value = "currpage", required = false) currpage: String?): String {
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage: Int// 总页数
        var totalrow: Int// 总行数
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        val users = usersService!!.userList()
        val parameters = HashMap<String, Any?>()
        val approvesall = approveService!!.queryApproves(parameters)

        parameters["crusername"] = cr.crusername
        parameters["craiid"] = cr.craiid
        val crsize = certifrecordService!!.queryCertifrecord(parameters)
        totalrow = crsize.size// 获取总行数

        totalpage = (totalrow + pagerow - 1) / pagerow

        if (currpages < 1) {
            currpages = 1
        }

        if (currpages > totalpage) {
            if (totalpage != 0) {
                currpages = totalpage
            } else {
                currpages = 1
            }
        }


        val candp = (currpages - 1) * pagerow
        parameters["pandc"] = pagerow
        parameters["candp"] = candp

        val certifrecords = certifrecordService.queryCertifrecord(parameters)
        model.addAttribute("users", users)
        model.addAttribute("approves", approvesall)
        model.addAttribute("cr", certifrecords)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)
        model.addAttribute("username", cr.crusername)
        model.addAttribute("apid", cr.craiid)
        //System.out.println("userssize=="+users.size()+"  certifrecordssize=="+certifrecords.size()+"  approvesallsize=="+approvesall.size());
        return str + "approvestatistics"
    }
    //资料认证 end
    //============================================================================================================================================

    //============================================================================================================================================
    //信用额度申请
    @RequestMapping("limitApplyfor")
    private fun limitApplyfor(model: Model,
                              @RequestParam(value = "currpage", required = false) currpage: String?,
                              @RequestParam(value = "mindate", required = false) mindate: String?,
                              @RequestParam(value = "clpuname", required = false) clpuname: String,
                              @RequestParam(value = "maxdate", required = false) maxdate: String?,
                              @RequestParam(value = "clpstate", required = false) clpstate: String): String {
        val pagerow = 10// 每页5行
        var currpages = 1// 当前页
        var totalpage: Int// 总页数
        var totalrow: Int// 总行数
        val format = SimpleDateFormat("yyyy-MM-dd")
        var date1: Date? = null
        var date2: Date? = null
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        try {
            if (mindate != null && mindate != "") {
                date1 = format.parse(mindate)
            }
            if (maxdate != null && maxdate != "") {
                date2 = format.parse(maxdate)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val pm = HashMap<String, Any?>()
        val certifrecords = certifrecordService!!.queryCertifrecord(pm)
        pm["clpuname"] = clpuname
        pm["maxdate"] = date2
        pm["mindate"] = date1
        pm["clpstate"] = clpstate
        val sizes = clapplyforService!!.queryClapplyfors(pm)

        totalrow = sizes.size// 获取总行数

        totalpage = (totalrow + pagerow - 1) / pagerow

        if (currpages < 1) {
            currpages = 1
        }

        if (currpages > totalpage) {
            if (totalpage != 0) {
                currpages = totalpage
            } else {
                currpages = 1
            }
        }

        val candp = (currpages - 1) * pagerow
        pm["pandc"] = pagerow
        pm["candp"] = candp
        val clapplyfors = clapplyforService.queryClapplyfors(pm)
        model.addAttribute("cr", certifrecords)
        model.addAttribute("cps", clapplyfors)
        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)
        model.addAttribute("maxdate", maxdate)
        model.addAttribute("clpuname", clpuname)
        model.addAttribute("mindate", mindate)
        model.addAttribute("clpstate", clpstate)
        //System.out.println("进来了========================================================clapplyfors=="+clapplyfors.size());

        return str + "limitapplyforlist"

    }

    @RequestMapping("applyforApprove")
    @ResponseBody
    private fun applyforApprove(ca: Clapplyfor): String {
        var code = "200"
        var  updateCode = clapplyforService!!.updateClapplyforState(BeanUtils.toMap(ca)!!)
        //System.out.println("clpid=="+ca.getClpid()+"   clpubcid=="+ca.getClpubcid()+"   clpporiginal=="+ca.getClpporiginal()+"   clpf=="+ca.getClpf()+"   clpstate=="+ca.getClpstate());
        if (ca.clpstate == "1") {//审核通过，修改申请表状态，增加信用额度

            val creditlimit = Creditlimit()
            creditlimit.cllimit = ca.clpporiginal!! + ca.clpf!!
            creditlimit.crbankcard = ca.clpubcid

        }
        if (updateCode <= 0) {
            code = "400"
        }
        return code
    }

}
