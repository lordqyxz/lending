package com.p2p.lending.controller

import com.p2p.lending.entity.InvestInfo
import com.p2p.lending.entity.Product
import com.p2p.lending.entity.Trade
import com.p2p.lending.entity.Users
import com.p2p.lending.service.*
import com.p2p.lending.util.BeanUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal
import java.sql.Timestamp
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.Resource
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


@Controller
@RequestMapping("/invest")
class InvestController {
    internal var httpSession: HttpSession? = null
    internal var servletContext: ServletContext? = null
    @Resource
    private val investService: InvestService? = null
    @Resource
    private// private BorrowmoneyService service;
    val productService: ProductService? = null
    @Resource
    private val detailsService: DetailsService? = null
    @Resource
    private val biaoService: BiaoService? = null
    @Resource
    private val certificationService: CertificationService? = null
    @Resource
    private val tradeService: TradeService? = null

    @RequestMapping("investSel")
    fun investSel(req: HttpServletRequest, model: Model, item: String?,
                  param: String, currpage: String?): String {// 查询出标列表 @RequestParam(value =
        // "currpage", required = false)
        // Borrowmoney borrowmoney=new Borrowmoney();
        // PageInfo<Borrowmoney> page = service.findList(borrowmoney, 1, 1);
        // List<Borrowmoney> list = page.getList();
        // System.out.println(">>>>>>>>>>>>>>>>>>>>总标数"+list.size()+list.toString());
        // model.addAttribute("list", list);
        //		String lastUrl = req.getHeader("Referer");//获取跳转过来页面的url
        //		System.out.println("上一个页面的URL"+lastUrl);
        //		String nowUrl = "http://localhost:8080/p2p/invest/investSel.do";
        //		boolean bl = false;
        //		if(lastUrl != null){
        //			bl = lastUrl.matches(nowUrl+"[\\s\\S]*");//正则表达式匹配nowUrl字符后的任意字符串
        //		}
        //
        //		System.out.println("匹配的结果： "+bl);
        //		if(!bl){//!lastUrl.equals(nowUrl)
        //			/////////////////此段代码可清除所有session
        //			  Enumeration em = req.getSession().getAttributeNames();
        //			  while(em.hasMoreElements()){
        //				req.getSession().removeAttribute(em.nextElement().toString());
        //			  }
        //			  //////////////
        //		}

        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数

        var outcount = 0// 不够一页的数据条数
        var count = 0//

        if (item != null && item != "") {//!lastUrl.equals(nowUrl)  && bl
            val map = HashMap<String, Any>()
            if (httpSession == null) {
                httpSession = req.session
            }
            if (item == "itemtype") {// 项目类型
                if (param == "-1") {// 不限
                    if (httpSession!!.getAttribute("biaoId") != null) {
                        println("进来了")
                        httpSession!!.removeAttribute("biaoId")
                    }
                } else {
                    httpSession!!.setAttribute("biaoId", param)
                }
            }
            if (item == "rate") {// 利率
                if (param == "-1") {// 不限
                    if (httpSession!!.getAttribute("pincome") != null) {
                        httpSession!!.setAttribute("startR", "-1")
                        httpSession!!.removeAttribute("pincome")
                    }
                }
                if (param == "1") {// 12%以下
                    httpSession!!.setAttribute("startR", "0")
                    httpSession!!.setAttribute("endR", "12")
                    httpSession!!.setAttribute("pincome", "")
                }
                if (param == "2") {// 12%-14%
                    httpSession!!.setAttribute("startR", "12")
                    httpSession!!.setAttribute("endR", "14")
                    httpSession!!.setAttribute("pincome", "")
                }
                if (param == "3") {// 14%-16%
                    httpSession!!.setAttribute("startR", "14")
                    httpSession!!.setAttribute("endR", "16")
                    httpSession!!.setAttribute("pincome", "")
                }
                if (param == "4") {// 16%及以上
                    httpSession!!.setAttribute("startR", "16")
                    httpSession!!.setAttribute("pincome", "")
                    httpSession!!.setAttribute("endR", "")
                }
            }
            if (item == "timelimit") {// 期限 此处默认一个月为30天
                if (param == "-1") {// 不限
                    if (httpSession!!.getAttribute("pcount") != null) {
                        httpSession!!.setAttribute("startT", "-1")
                        httpSession!!.removeAttribute("pcount")
                    }
                }
                if (param == "1") {// 1月以下
                    httpSession!!.setAttribute("startT", "0")
                    httpSession!!.setAttribute("endT", "30")
                    httpSession!!.setAttribute("pcount", "")
                }
                if (param == "2") {// 1-3月
                    httpSession!!.setAttribute("startT", "30")
                    httpSession!!.setAttribute("endT", "90")
                    httpSession!!.setAttribute("pcount", "")
                }
                if (param == "3") {// 3-6月
                    httpSession!!.setAttribute("startT", "90")
                    httpSession!!.setAttribute("endT", "180")
                    httpSession!!.setAttribute("pcount", "")
                }
                if (param == "4") {// 6-12月
                    httpSession!!.setAttribute("startT", "180")
                    httpSession!!.setAttribute("endT", "360")
                    httpSession!!.setAttribute("pcount", "")
                }
                if (param == "5") {// 12月及以上
                    httpSession!!.setAttribute("startT", "360")
                    httpSession!!.setAttribute("endT", "")
                    httpSession!!.setAttribute("pcount", "")
                }
            }
            if (item == "repayway") {// 还款方式
                if (param == "-1") {// 不限
                    httpSession!!.setAttribute("pway", "")
                }
                if (param == "1") {
                    httpSession!!.setAttribute("pway", "到期还本付息")
                }
                if (param == "2") {
                    httpSession!!.setAttribute("pway", "按月付息,到期还本")
                }
                if (param == "3") {
                    httpSession!!.setAttribute("pway", "等额本息")
                }

            }
            // +httpSession.getAttribute("endR")==null"": +httpSession.getAttribute("endT")
            println("session中的标主键 " + httpSession!!.getAttribute("biaoId") + ""
                    + "session中的利率开始点  " + httpSession!!.getAttribute("startR")
                    + "session中的期限开始点" + httpSession!!.getAttribute("startT")
                    + "session中的还款方式" + httpSession!!.getAttribute("way"))
            println("map中的标主键 " + map["biaoId"] + "利率开始点 "
                    + map["startR"] + "期限开始点 " + map["startT"]
                    + "还款方式 " + map["way"])
            if (httpSession != null) {
                map["pincome"] = httpSession!!.getAttribute("pincome")
                map["pcount"] = httpSession!!.getAttribute("pcount")

                map["biaoId"] = httpSession!!.getAttribute("biaoId")
                map["startR"] = httpSession!!.getAttribute("startR")
                map["endR"] = httpSession!!.getAttribute("endR")
                map["startT"] = httpSession!!.getAttribute("startT")
                map["endT"] = httpSession!!.getAttribute("endT")
                map["pway"] = httpSession!!.getAttribute("pway")
            }
            println("map中的标主键 " + map["biaoId"] + "利率开始点 "
                    + map["startR"] + "期限开始点 " + map["startT"]
                    + "还款方式 " + map["pway"])

            val page = productService!!.selList(map)

            totalrow = page.size// 获取总行数
            if (currpage != null && "" != currpage) {
                currpages = Integer.parseInt(currpage)
            }
            // totalpage = (totalrow + pagerow - 1) / pagerow;

            outcount = totalrow % pagerow
            count = totalrow / pagerow

            totalpage = count

            if (outcount > 0) {
                totalpage = count + 1
            }

            if (currpages < 1) {
                currpages = 1
            }
            if (currpages > totalpage) {
                currpages = totalpage
            }

            if (currpages == 0) {
                currpages = 1
            }

            val candp = (currpages - 1) * pagerow
            map["startPage"] = candp
            map["pageSize"] = 5

            val pages = productService.selList(map)
            model.addAttribute("totalrow", totalrow)
            model.addAttribute("currpages", currpages)
            model.addAttribute("totalpage", totalpage)
            model.addAttribute("list", pages)

        } else {

            val pro = Product()
            val page = productService!!.findList(BeanUtils.toMap(pro)!!)

            totalrow = page.size// 获取总行数
            if (currpage != null && "" != currpage) {
                currpages = Integer.parseInt(currpage)
            }
            // totalpage = (totalrow + pagerow - 1) / pagerow;

            outcount = totalrow % pagerow
            count = totalrow / pagerow

            totalpage = count

            if (outcount > 0) {
                totalpage = count + 1
            }

            if (currpages < 1) {
                currpages = 1
            }
            if (currpages > totalpage) {
                currpages = totalpage
            }

            if (currpages == 0) {
                currpages = 1
            }

            val candp = (currpages - 1) * pagerow
            pro.startPage = candp
            pro.pageSize = 5

            val list = productService.findList(BeanUtils.toMap(pro)!!)
            model.addAttribute("totalrow", totalrow)
            model.addAttribute("currpages", currpages)
            model.addAttribute("totalpage", totalpage)
            model.addAttribute("list", list)
        }

        val map = HashMap<String, Any>()
        val biao = biaoService!!.findList(map)
        model.addAttribute("biao", biao)

        return "list"
    }

    @RequestMapping("recommendShow")
    fun recommendShow(req: HttpServletRequest, model: Model): String {// 首页中显示推荐
        val parameters = HashMap<String, Any>()// 查询条件

        // parameters.put("pandc", 2);
        // parameters.put("candp", 1);
        // parameters.put("bstate", "招标中");
        // parameters.put("brecommend", "推荐");

        if (servletContext == null) {
            val proList = ArrayList<Product>()

            val list = biaoService!!.findList(parameters)
            if (list != null && list.size > 0) {
                parameters["pageSize"] = 2
                parameters["startPage"] = 0
                for (i in list.indices) {
                    val biao = list[i]
                    parameters["biaoId"] = biao.id!!
                    val tlist = productService!!.selList(parameters)// 两条数据
                    for (j in tlist.indices) {
                        proList.add(tlist[j])// 将每个类型的两条数据保存到一个list中
                    }
                }
            }

            parameters.remove("biaoId")
            parameters["pcount"] = ""//推荐项目期限一个月以下
            parameters["startT"] = "0"
            parameters["endT"] = "30"
            val tjl = productService!!.selList(parameters)

            //				model.addAttribute("proList", proList);
            //				model.addAttribute("biaoList", list);
            val map = HashMap<String, Any>()
            map["rowName"] = "inmoney"// 查出投资总额
            map["tableName"] = "investinfo"

            val tm = investService!!.sumMoney(map)

            servletContext = req.session.servletContext
            servletContext!!.setAttribute("proList", proList)
            servletContext!!.setAttribute("biaoList", list)
            servletContext!!.setAttribute("tjlist", tjl)
            servletContext!!.setAttribute("ztz", tm)
        }

        // List<Borrowmoney> list = service.pagingSel(parameters);
        // model.addAttribute("list", list);

        return "index"
    }

    @RequestMapping("investInfo")
    fun investInfo(
            bmid: String, currpage: String?,
            model: Model, req: HttpServletRequest): String {// Borrowmoney bm
        println(bmid)
        // Borrowmoney bm = service.get(Integer.parseInt(bmid));
        // System.out.println(bm.toString());
        // model.addAttribute("Borrowmoney", bm);
        // HttpSession bms = req.getSession();
        // bms.setAttribute("Borrowmoney", bm);
        ////////////////////////////////////
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数

        var outcount = 0// 不够一页的数据条数
        var count = 0//

        val parameters = HashMap<String, Any>()// 查询条件
        parameters["bid"] = bmid
        val page = investService!!.investS(parameters)// 查出数据条数
        totalrow = page.size// 获取总行数
        println("此标的投资信息记录条数$totalrow")
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }

        outcount = totalrow % pagerow
        count = totalrow / pagerow

        totalpage = count

        if (outcount > 0) {
            totalpage = count + 1
        }

        if (currpages < 1) {
            currpages = 1
        }
        if (currpages > totalpage) {
            currpages = totalpage
        }

        var candp: Int? = (currpages - 1) * pagerow
        if (candp!! < 0) {
            candp = 0
        }
        parameters["pandc"] = 5
        parameters["candp"] = candp

        val lists = investService.investS(parameters)

        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)
        model.addAttribute("bmid", bmid)
        model.addAttribute("record", lists)

        // 查出一些总额
        val map = HashMap<String, Any>()
        map["rowName"] = "inmoney"// 查出投资总额
        map["tableName"] = "investinfo"
        map["bid"] = bmid//获得此标的所有投资记录

        val tm = investService.sumMoney(map)
        model.addAttribute("tm", tm)
        println("tm" + tm!!)
        map["rowName"] = "profitmoney"// 查出收益总额

        val gm = investService.sumMoney(map)
        model.addAttribute("gm", gm)
        println("gm" + gm!!)

        val bmap = HashMap<String, Any>()
        val biao = biaoService!!.findList(bmap)
        model.addAttribute("biao", biao)
        //////////////////////////////////////////


        val pro = productService!![Integer.parseInt(bmid)]
        val bms = req.session
        bms.setAttribute("Borrowmoney", pro)

        val list = detailsService!!.detailslist(pro.id)
        println("标详情列表大小" + list.size)
        bms.setAttribute("Product", pro)
        bms.setAttribute("Details", list)

        println("pro.getPstate()获取到的值为   " + pro.pstate!!)
        val days = (pro.pcount!!.time - pro.ptime!!.time) / (24 * 60 * 60 * 1000)
        bms.setAttribute("days", days)
        if (pro.pstate == "1") {
            val us = req.session.getAttribute("globaluser") as Users
            if (us != null) {
                val kymoney = certificationService!!.selectM(us.uid)
                println("进入到输入金额页面  用户余额$kymoney")
                bms.setAttribute("kymoney", kymoney)
            }
            return "inforadd"
        } else {
            println("进入到显示页面")
            return "infor"
        }
    }

    @RequestMapping("investAdd")
    fun investAdd(
            @RequestParam(value = "money", required = false) money: String,
            req: HttpServletRequest, model: Model): String {// 投标
        // @RequestParam(value="",requested=false)
        // InvestInfo ii
        val hs = req.session
        // Borrowmoney bm = (Borrowmoney) httpSession.getAttribute("Borrowmoney");
        // System.out.println(bm.getBlimit());

        val pro = hs.getAttribute("Product") as Product

        val ii = InvestInfo()
        val user = hs.getAttribute("globaluser") as Users
        // inid; //'投资信息表主键',
        // ii.setInid(2);
        if (user != null) {
            ii.userid = user.uid!! // '投资用户主键',
            // ii.setBrrowid(bm.getId()); //'投标的主键',
            ii.brrowid = pro.id!!//
            ii.inmoney = BigDecimal(money) // '投资金额',
            ii.instatus = "不用" // '投资状态 0 收益中的投资 1已完成的投资',
            ii.instyle = "不用" // '投资类型',
            // brrowstatus;// '借贷状态是否凑资完',
            ii.brrowstatus = "不用"
            ii.interest = pro.pincome // '投资利率',
            ii.profitmodel = pro.pway // '盈利方式 如等额本金',
            ii.profitmoney = BigDecimal("0.00") // '盈利金额',
            val date = Date()
            // @SuppressWarnings("deprecation")
            // String d = date.toLocaleString();
            val ts = Timestamp(date.year, date.month,
                    date.day, date.hours, date.minutes,
                    date.seconds, 0)
            ii.indate = ts // '投资时间，可为空'

            // ii.setReplaydate(Integer.parseInt(bm.getBlimit())); //
            val days = (pro.pcount!!.time - pro.ptime!!.time) / (24 * 60 * 60 * 1000)// 相差几天
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val ds = sdf.format(pro.pcount)

            ii.replaydate = ds + "(共" + days + "天)"
            ii.markstatus = 0 // '投标状态 0默认投标中 1 投标通过（中标） 2投标未通过（失标）';

            println(ii.toString())
            hs.removeAttribute("Product")
            hs.removeAttribute("Details")
            investService!!.investA(ii)//添加投资记录

            //从用户可用余额中扣除金额
            val map = HashMap<String, Any>()
            map["uid"] = user.uid!!
            val kym = req.session.getAttribute("kymoney") as String//可用总金额
            val nkym = (java.lang.Double.parseDouble(kym) - java.lang.Double.parseDouble(money)).toString() + ""//扣除投资后剩余的可用金额
            map["money"] = nkym
            certificationService!!.updateM(map)
            hs.removeAttribute("kymoney")
            //写入用户账户金额记录表
            val td = Trade()
            td.setuID(user.uid)
            td.uname = user.unickname
            td.zname = user.uname
            td.jymoney = money
            td.other = "要投资就要舍得花钱"
            tradeService!!.addDate(td)
            //修改项目凑集资金
            //			Product product = productService.get(pro.getId());
            val updMoney = java.lang.Double.parseDouble(pro.pmoney!!.toString() + "") + java.lang.Double.parseDouble(money)
            println("修改完后的金额$updMoney")
            pro.pmoney = updMoney.toInt()
            productService!!.update(pro)
            //判断项目是否满标
            val df = DecimalFormat("0.00")
            val udm = df.format(updMoney)
            val odm = df.format(pro.ptotalmoney)
            if (udm == odm) {//刚好凑集完
                pro.pstate = "2"//修改为凑资完
                productService.update(pro)
            }
            hs.setAttribute("end", "end")
        }

        return "redirect:investInfo.do?bmid=" + pro.id!!
    }

    @RequestMapping("investRecord")
    fun investRecord(model: Model,
                     @RequestParam(value = "currpage", required = false) currpage: String?, req: HttpServletRequest): String {// 查出投资记录
        val u = req.session.getAttribute("globaluser") as Users

        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        var totalrow = 0// 总行数

        var outcount = 0// 不够一页的数据条数
        var count = 0//

        val parameters = HashMap<String, Any?>()// 查询条件
        if (u != null) {//用户已登录就查出此用户的数据否则所有数据
            parameters["uid"] = u.uid!!
        }
        val page = investService!!.investS(parameters)// 查出数据条数
        totalrow = page.size// 获取总行数
        println("此标的投资信息记录条数$totalrow")
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        // totalpage = (totalrow + pagerow - 1) / pagerow;

        outcount = totalrow % pagerow
        count = totalrow / pagerow

        totalpage = count

        if (outcount > 0) {
            totalpage = count + 1
        }

        if (currpages < 1) {
            currpages = 1
        }
        if (currpages > totalpage) {
            currpages = totalpage
        }
        // Integer pandc = pagerow * currpages;
        var candp: Int = (currpages - 1) * pagerow
        if (candp < 0) {
            candp = 0
        }
        parameters["pandc"] = 5
        parameters["candp"] = candp
        val list = investService.investS(parameters)

        model.addAttribute("totalrow", totalrow)
        model.addAttribute("currpages", currpages)
        model.addAttribute("totalpage", totalpage)
        model.addAttribute("record", list)

        // 查出一些总额
        val map = HashMap<String, Any>()
        map["rowName"] = "inmoney"// 查出投资总额
        map["tableName"] = "investinfo"
        if (u != null) {//用户已登录就查出此用户的数据否则所有数据
            map["uid"] = u.uid!!
        }

        val tm = investService.sumMoney(map)//查出投资总额
        model.addAttribute("tm", tm)
        println("tm" + tm!!)
        map["rowName"] = "profitmoney"

        val gm = investService.sumMoney(map)// 查出收益总额
        model.addAttribute("gm", gm)
        println("gm" + gm!!)

        //查出退还的本金
        val tmonery = tradeService!!.selectMoney(u.uid)
        var allM: Int? = 0
        for (tr in tmonery) {
            val money = tr.jymoney!!.replace("+", "")
            allM = allM?.plus(Integer.parseInt(money))
        }
        println("退还本金总额" + allM!!)

        //查出总收益
        val gtm = investService.getMoney(u.uid)
        model.addAttribute("gtm", gtm)

        val bmap = HashMap<String, Any>()
        val biao = biaoService!!.findList(bmap)
        model.addAttribute("biao", biao)
        model.addAttribute("thm", allM)
        return "investrecord"
    }

    //	public static void main(String s[]) {
    //		Date date = new Date();
    //		long dl = date.getTime();// 将日期转换成毫秒数
    //		System.out.println(dl + "");
    //		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //		Date d = new Date();
    //		try {
    //			d = sdf.parse("2017-03-05 20:27:00");
    //		} catch (ParseException e) {
    //			e.printStackTrace();
    //		}
    //		long dt = d.getTime();
    //		long day = (dt - dl) / (24 * 60 * 60 * 1000);
    //		System.out.println(day + "天");
    //	}
}
