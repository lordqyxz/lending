package com.p2p.lending.controller

import com.p2p.lending.entity.Certifrecord
import com.p2p.lending.entity.Dope
import com.p2p.lending.entity.Poundage
import com.p2p.lending.service.CertificationService
import com.p2p.lending.service.DopeService
import com.p2p.lending.service.InformationService
import com.p2p.lending.service.PoundageService
import com.p2p.lending.util.BeanUtils
import com.p2p.lending.util.CreateRandom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest

@Controller
open class InformationController {
    @Autowired
    private val informationService: InformationService? = null
    @Autowired
    private val poundageService: PoundageService? = null
    @Autowired
    private val certificationService: CertificationService? = null
    @Autowired
    private val dopeService: DopeService? = null

    // 我的账户
    @RequestMapping("query")
    fun query(@RequestParam(value = "id", required = false) id: String, model: Model): String {
        val map = HashMap<String, Any>()
        map["id"] = id
        val user = informationService!!.query(map)
        model.addAttribute("user", user)
        return "personalpage"
    }

    // 账户信息查询
    @RequestMapping("find")
    fun find(@RequestParam(value = "id", required = false) id: String, model: Model,
             request: HttpServletRequest): String {
        val map = HashMap<String, Any>()
        map["id"] = id
        val user = informationService!!.find(map)
        val list = informationService.appquery()
        request.setAttribute("num", user.uphonenumber)
        request.setAttribute("mailbox", user.umailbox)
        val app = list[0]
        model.addAttribute("list", app)
        model.addAttribute("user", user)

        return "account"
    }

    // 添加身份信息和认证信息
    @RequestMapping("insertUsercre")
    fun insert(@RequestParam(value = "uid", required = false) uid: Int?,
               @RequestParam(value = "unickname", required = false) unickname: String,
               @RequestParam(value = "aiid", required = false) aiid: Int?,
               @RequestParam(value = "ainame", required = false) ainame: String,
               @RequestParam(value = "realname", required = false) realname: String,
               @RequestParam(value = "idnumbers", required = false) IDnumber: String, cer: Certifrecord): String {
        println(
                "\t" + uid + "\t" + unickname + "\t" + aiid + "\t" + ainame + "\t" + realname + "\t" + IDnumber)
        val map = HashMap<String, Any>()
        map["uname"] = realname
        map["ucardid"] = IDnumber
        map["uid"] = uid!!
        informationService!!.addUsers(map)
        cer.cruserid = uid
        cer.crusername = unickname
        cer.craiid = aiid
        cer.crainame = ainame
        informationService.addcertifrecord(cer)
        return "redirect:/find.do?id=" + uid!!
    }

    //模拟添加第三方身份信息
    @RequestMapping("insertucertnum")
    fun insertucertnumber(@RequestParam(value = "id", required = false) id: String,
                          @RequestParam(value = "uname", required = false) uname: String,
                          @RequestParam(value = "ucardid", required = false) ucardid: String,
                          @RequestParam(value = "umailbox", required = false) umailbox: String,
                          @RequestParam(value = "uphonenumber", required = false) uphonenumber: String,
                          @RequestParam(value = "upwd_zd", required = false) upwd_zd: String): String {
        val stringObjectHashMap = HashMap<String, Any>()
        stringObjectHashMap["id"] = id
        val ucertnumber = ((Math.random() * 9 + 1) * 100000).toInt()
        val s = ucertnumber.toString()
        stringObjectHashMap["ucertnumber"] = s
        stringObjectHashMap["uname"] = uname
        stringObjectHashMap["ucardid"] = ucardid
        stringObjectHashMap["umailbox"] = umailbox
        stringObjectHashMap["uphonenumber"] = uphonenumber
        stringObjectHashMap["upwd_zd"] = upwd_zd
        informationService!!.upucertnum(stringObjectHashMap)
        return "redirect:/find.do?id=$id"
    }

    //获取金额，提现
    @RequestMapping("withdraw")
    fun withdraw(@RequestParam(value = "id", required = false) id: Int?, model: Model): String {
        model.addAttribute("cer", certificationService!!.select(id))
        return "Withdraw"
    }

    //获取金额，提现
    @RequestMapping("withdrawpay")
    fun withdrawpay(@RequestParam(value = "id", required = true) id: String,
                    @RequestParam(value = "actualMoney", required = true) actualMoney: String): String {
        val certi = certificationService!!.select(Integer.parseInt(id))
        val mnum = certi.cbalance
        val znum = certi.ctotalmoney
        val mf = java.lang.Float.parseFloat(mnum!!)
        val af = java.lang.Float.parseFloat(actualMoney)
        val afb = java.lang.Float.parseFloat(znum!!)
        val amf = mf - af
        val bmf = afb - af
        val map = HashMap<String, Any>()
        map["uid"] = id
        map["cbalance"] = amf.toString()
        map["ctotalmoney"] = bmf.toString()
        certificationService.upm(map)

        return "redirect:/withdraw.do?id=" + Integer.parseInt(id)
    }


    // 修改用户密码
    @RequestMapping("updpassword")
    fun updpassword(@RequestParam(value = "id", required = false) id: String,
                    @RequestParam(value = "updatePassForm:repassword", required = false) password: String): String {
        val map = HashMap<String, Any>()
        map["id"] = id
        map["password"] = password
        informationService!!.updPassword(map)
        return "redirect:/find.do?id=$id"
    }

    //修改手机号码
    @RequestMapping("updphone")
    fun updphone(@RequestParam(value = "id", required = false) id: String,
                 @RequestParam(value = "newPhone", required = false) newPhone: String): String {
        println("用户id:" + id + "新手机号:" + newPhone)
        val map = HashMap<String, Any>()
        map["id"] = id
        map["phone"] = newPhone
        informationService!!.updphone(map)
        return "redirect:/find.do?id=$id"
    }

    //获取验证码
    @RequestMapping("identifying")
    @ResponseBody
    fun identifying(): Int {
        val i = CreateRandom.random()
        try {
            println("-------")
            println("随机数$i")
            val fileWriter: FileWriter
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            val date = Date()
            fileWriter = FileWriter("E://test.txt")
            fileWriter.write("发送时间:" + format.format(date) + "验证码:" + i)
            fileWriter.flush()
            fileWriter.close()
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return i
    }

    //用户充值
    @RequestMapping("userpay")
    @ResponseBody
    @Transactional
    open fun userpay(po: Poundage): String {
        val code = "200"
        val date = Date()
        val usermap = HashMap<String, Any>()
        val map = HashMap<String, Any>()
        usermap["id"] = po.getuID()!!
        val user = informationService!!.find(usermap)
        po.uname = user.unickname
        po.zname = user.uname
        po.what = "充值"
        po.sxtime = date
        po.bookaccount = user.uid!!.toString() + ""
        po.paytype = "快捷支付"
        val certi = certificationService!!.select(po.getuID())
        //可用余额
        val cbal = certi.cbalance
        val xmoney = po.sxmoney
        val fmoney = java.lang.Float.valueOf(cbal!!) + java.lang.Float.valueOf(xmoney!!)
        //总余额
        val moneyString = certi.ctotalmoney
        val money = java.lang.Float.valueOf(moneyString!!) + java.lang.Float.valueOf(xmoney)
        map["id"] = po.getuID()!!
        map["cbalance"] = fmoney.toString()
        map["ctotalmoney"] = money.toString()
        val dope = Dope()
        dope.dprimkey = po.getuID()
        dope.dtitle = "充值成功"
        dope.details = "尊敬的" + user.unickname + ",您通过" + po.paytype + "充值的" + po.sxmoney + "元已到账!"
        dope.dtime = date
        //增加充值明细表数据
        poundageService!!.insert(po)
        //增加账户金额数据
        certificationService.undate(map)
        //添加广播数据
        dopeService!!.insert(dope)
        return code
    }

    //用户资金记录
    @RequestMapping("listpay")
    fun listpay(model: Model, @RequestParam(value = "currpage", required = false) currpage: String?, @RequestParam(value = "id", required = false) id: String): String? {
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数
        val poundage = Poundage()
        val list = poundageService!!.findList(BeanUtils.toMap(poundage)!!)
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
        return null
    }

}
