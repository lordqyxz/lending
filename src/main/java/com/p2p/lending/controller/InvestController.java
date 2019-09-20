package com.p2p.lending.controller;

import com.p2p.lending.entity.*;
import com.p2p.lending.service.*;
import com.p2p.lending.util.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/invest")
public class InvestController {
    HttpSession httpSession = null;
    ServletContext servletContext = null;
    @Resource
    private InvestService investService;
    @Resource
    // private BorrowmoneyService service;
    private ProductService productService;
    @Resource
    private DetailsService detailsService;
    @Resource
    private BiaoService biaoService;
    @Resource
    private CertificationService certificationService;
    @Resource
    private TradeService tradeService;

    @RequestMapping("investSel")
    public String investSel(HttpServletRequest req, Model model, String item,
                            String param, String currpage) {// 查询出标列表 @RequestParam(value =
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

        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数

        int outcount = 0;// 不够一页的数据条数
        int count = 0;//

        if (item != null && !item.equals("")) {//!lastUrl.equals(nowUrl)  && bl
            Map<String, Object> map = new HashMap<String, Object>();
            if (httpSession == null) {
                httpSession = req.getSession();
            }
            if (item.equals("itemtype")) {// 项目类型
                if (param.equals("-1")) {// 不限
                    if (httpSession.getAttribute("biaoId") != null) {
                        System.out.println("进来了");
                        httpSession.removeAttribute("biaoId");
                    }
                } else {
                    httpSession.setAttribute("biaoId", param);
                }
            }
            if (item.equals("rate")) {// 利率
                if (param.equals("-1")) {// 不限
                    if (httpSession.getAttribute("pincome") != null) {
                        httpSession.setAttribute("startR", "-1");
                        httpSession.removeAttribute("pincome");
                    }
                }
                if (param.equals("1")) {// 12%以下
                    httpSession.setAttribute("startR", "0");
                    httpSession.setAttribute("endR", "12");
                    httpSession.setAttribute("pincome", "");
                }
                if (param.equals("2")) {// 12%-14%
                    httpSession.setAttribute("startR", "12");
                    httpSession.setAttribute("endR", "14");
                    httpSession.setAttribute("pincome", "");
                }
                if (param.equals("3")) {// 14%-16%
                    httpSession.setAttribute("startR", "14");
                    httpSession.setAttribute("endR", "16");
                    httpSession.setAttribute("pincome", "");
                }
                if (param.equals("4")) {// 16%及以上
                    httpSession.setAttribute("startR", "16");
                    httpSession.setAttribute("pincome", "");
                    httpSession.setAttribute("endR", "");
                }
            }
            if (item.equals("timelimit")) {// 期限 此处默认一个月为30天
                if (param.equals("-1")) {// 不限
                    if (httpSession.getAttribute("pcount") != null) {
                        httpSession.setAttribute("startT", "-1");
                        httpSession.removeAttribute("pcount");
                    }
                }
                if (param.equals("1")) {// 1月以下
                    httpSession.setAttribute("startT", "0");
                    httpSession.setAttribute("endT", "30");
                    httpSession.setAttribute("pcount", "");
                }
                if (param.equals("2")) {// 1-3月
                    httpSession.setAttribute("startT", "30");
                    httpSession.setAttribute("endT", "90");
                    httpSession.setAttribute("pcount", "");
                }
                if (param.equals("3")) {// 3-6月
                    httpSession.setAttribute("startT", "90");
                    httpSession.setAttribute("endT", "180");
                    httpSession.setAttribute("pcount", "");
                }
                if (param.equals("4")) {// 6-12月
                    httpSession.setAttribute("startT", "180");
                    httpSession.setAttribute("endT", "360");
                    httpSession.setAttribute("pcount", "");
                }
                if (param.equals("5")) {// 12月及以上
                    httpSession.setAttribute("startT", "360");
                    httpSession.setAttribute("endT", "");
                    httpSession.setAttribute("pcount", "");
                }
            }
            if (item.equals("repayway")) {// 还款方式
                if (param.equals("-1")) {// 不限
                    httpSession.setAttribute("pway", "");
                }
                if (param.equals("1")) {
                    httpSession.setAttribute("pway", "到期还本付息");
                }
                if (param.equals("2")) {
                    httpSession.setAttribute("pway", "按月付息,到期还本");
                }
                if (param.equals("3")) {
                    httpSession.setAttribute("pway", "等额本息");
                }

            }
            // +httpSession.getAttribute("endR")==null"": +httpSession.getAttribute("endT")
            System.out.println("session中的标主键 " + httpSession.getAttribute("biaoId") + ""
                    + "session中的利率开始点  " + httpSession.getAttribute("startR")
                    + "session中的期限开始点" + httpSession.getAttribute("startT")
                    + "session中的还款方式" + httpSession.getAttribute("way"));
            System.out.println("map中的标主键 " + map.get("biaoId") + "利率开始点 "
                    + map.get("startR") + "期限开始点 " + map.get("startT")
                    + "还款方式 " + map.get("way"));
            if (httpSession != null) {
                map.put("pincome", httpSession.getAttribute("pincome"));
                map.put("pcount", httpSession.getAttribute("pcount"));

                map.put("biaoId", httpSession.getAttribute("biaoId"));
                map.put("startR", httpSession.getAttribute("startR"));
                map.put("endR", httpSession.getAttribute("endR"));
                map.put("startT", httpSession.getAttribute("startT"));
                map.put("endT", httpSession.getAttribute("endT"));
                map.put("pway", httpSession.getAttribute("pway"));
            }
            System.out.println("map中的标主键 " + map.get("biaoId") + "利率开始点 "
                    + map.get("startR") + "期限开始点 " + map.get("startT")
                    + "还款方式 " + map.get("pway"));

            List<Product> page = productService.selList(map);

            totalrow = page.size();// 获取总行数
            if (currpage != null && !"".equals(currpage)) {
                currpages = Integer.parseInt(currpage);
            }
            // totalpage = (totalrow + pagerow - 1) / pagerow;

            outcount = totalrow % pagerow;
            count = totalrow / pagerow;

            totalpage = count;

            if (outcount > 0) {
                totalpage = count + 1;
            }

            if (currpages < 1) {
                currpages = 1;
            }
            if (currpages > totalpage) {
                currpages = totalpage;
            }

            if (currpages == 0) {
                currpages = 1;
            }

            Integer candp = (currpages - 1) * pagerow;
            map.put("startPage", candp);
            map.put("pageSize", 5);

            List<Product> pages = productService.selList(map);
            model.addAttribute("totalrow", totalrow);
            model.addAttribute("currpages", currpages);
            model.addAttribute("totalpage", totalpage);
            model.addAttribute("list", pages);

        } else {

            Product pro = new Product();
            @SuppressWarnings("unchecked")
            List<Product> page = productService.findList(BeanUtils.toMap(pro));

            totalrow = page.size();// 获取总行数
            if (currpage != null && !"".equals(currpage)) {
                currpages = Integer.parseInt(currpage);
            }
            // totalpage = (totalrow + pagerow - 1) / pagerow;

            outcount = totalrow % pagerow;
            count = totalrow / pagerow;

            totalpage = count;

            if (outcount > 0) {
                totalpage = count + 1;
            }

            if (currpages < 1) {
                currpages = 1;
            }
            if (currpages > totalpage) {
                currpages = totalpage;
            }

            if (currpages == 0) {
                currpages = 1;
            }

            Integer candp = (currpages - 1) * pagerow;
            pro.setStartPage(candp);
            pro.setPageSize(5);

            @SuppressWarnings("unchecked")
            List<Product> list = productService.findList(BeanUtils.toMap(pro));
            model.addAttribute("totalrow", totalrow);
            model.addAttribute("currpages", currpages);
            model.addAttribute("totalpage", totalpage);
            model.addAttribute("list", list);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        List<Biao> biao = biaoService.findList(map);
        model.addAttribute("biao", biao);

        return "list";
    }

    @RequestMapping("recommendShow")
    public String recommendShow(HttpServletRequest req, Model model) {// 首页中显示推荐
        Map<String, Object> parameters = new HashMap<String, Object>();// 查询条件

        // parameters.put("pandc", 2);
        // parameters.put("candp", 1);
        // parameters.put("bstate", "招标中");
        // parameters.put("brecommend", "推荐");

        if (servletContext == null) {
            List<Product> proList = new ArrayList<Product>();

            List<Biao> list = biaoService.findList(parameters);
            if (list != null && list.size() > 0) {
                parameters.put("pageSize", 2);
                parameters.put("startPage", 0);
                for (int i = 0; i < list.size(); i++) {
                    Biao biao = list.get(i);
                    parameters.put("biaoId", biao.getId());
                    List<Product> tlist = productService.selList(parameters);// 两条数据
                    for (int j = 0; j < tlist.size(); j++) {
                        proList.add(tlist.get(j));// 将每个类型的两条数据保存到一个list中
                    }
                }
            }

            parameters.remove("biaoId");
            parameters.put("pcount", "");//推荐项目期限一个月以下
            parameters.put("startT", "0");
            parameters.put("endT", "30");
            List<Product> tjl = productService.selList(parameters);

//				model.addAttribute("proList", proList);
//				model.addAttribute("biaoList", list);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rowName", "inmoney");// 查出投资总额
            map.put("tableName", "investinfo");

            Double tm = investService.sumMoney(map);

            servletContext = req.getSession().getServletContext();
            servletContext.setAttribute("proList", proList);
            servletContext.setAttribute("biaoList", list);
            servletContext.setAttribute("tjlist", tjl);
            servletContext.setAttribute("ztz", tm);
        }

        // List<Borrowmoney> list = service.pagingSel(parameters);
        // model.addAttribute("list", list);

        return "index";
    }

    @RequestMapping("investInfo")
    public String investInfo(
            String bmid, String currpage,
            Model model, HttpServletRequest req) {// Borrowmoney bm
        System.out.println(bmid.toString());
        // Borrowmoney bm = service.get(Integer.parseInt(bmid));
        // System.out.println(bm.toString());
        // model.addAttribute("Borrowmoney", bm);
        // HttpSession bms = req.getSession();
        // bms.setAttribute("Borrowmoney", bm);
////////////////////////////////////
        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数

        int outcount = 0;// 不够一页的数据条数
        int count = 0;//

        Map<String, Object> parameters = new HashMap<String, Object>();// 查询条件
        parameters.put("bid", bmid);
        List<InvestInfo> page = investService.investS(parameters);// 查出数据条数
        totalrow = page.size();// 获取总行数
        System.out.println("此标的投资信息记录条数" + totalrow);
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }

        outcount = totalrow % pagerow;
        count = totalrow / pagerow;

        totalpage = count;

        if (outcount > 0) {
            totalpage = count + 1;
        }

        if (currpages < 1) {
            currpages = 1;
        }
        if (currpages > totalpage) {
            currpages = totalpage;
        }

        Integer candp = (currpages - 1) * pagerow;
        if (candp < 0) {
            candp = 0;
        }
        parameters.put("pandc", 5);
        parameters.put("candp", candp);

        List<InvestInfo> lists = investService.investS(parameters);

        model.addAttribute("totalrow", totalrow);
        model.addAttribute("currpages", currpages);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("bmid", bmid);
        model.addAttribute("record", lists);

        // 查出一些总额
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rowName", "inmoney");// 查出投资总额
        map.put("tableName", "investinfo");
        map.put("bid", bmid);//获得此标的所有投资记录

        Double tm = investService.sumMoney(map);
        model.addAttribute("tm", tm);
        System.out.println("tm" + tm);
        map.put("rowName", "profitmoney");// 查出收益总额

        Double gm = investService.sumMoney(map);
        model.addAttribute("gm", gm);
        System.out.println("gm" + gm);

        Map<String, Object> bmap = new HashMap<String, Object>();
        List<Biao> biao = biaoService.findList(bmap);
        model.addAttribute("biao", biao);
//////////////////////////////////////////


        Product pro = productService.get(Integer.parseInt(bmid));
        HttpSession bms = req.getSession();
        bms.setAttribute("Borrowmoney", pro);

        List<Details> list = detailsService.detailslist(pro.getId());
        System.out.println("标详情列表大小" + list.size());
        bms.setAttribute("Product", pro);
        bms.setAttribute("Details", list);

        System.out.println("pro.getPstate()获取到的值为   " + pro.getPstate());
        long days = (pro.getPcount().getTime() - pro.getPtime().getTime())
                / (24 * 60 * 60 * 1000);
        bms.setAttribute("days", days);
        if (pro.getPstate().equals("1")) {
            Users us = (Users) req.getSession().getAttribute("globaluser");
            if (us != null) {
                String kymoney = certificationService.selectM(us.getUid());
                System.out.println("进入到输入金额页面  用户余额" + kymoney);
                bms.setAttribute("kymoney", kymoney);
            }
            return "inforadd";
        } else {
            System.out.println("进入到显示页面");
            return "infor";
        }
    }

    @RequestMapping("investAdd")
    public String investAdd(
            @RequestParam(value = "money", required = false) String money,
            HttpServletRequest req, Model model) {// 投标
        // @RequestParam(value="",requested=false)
        // InvestInfo ii
        HttpSession hs = req.getSession();
        // Borrowmoney bm = (Borrowmoney) httpSession.getAttribute("Borrowmoney");
        // System.out.println(bm.getBlimit());

        Product pro = (Product) hs.getAttribute("Product");

        InvestInfo ii = new InvestInfo();
        Users user = (Users) hs.getAttribute("globaluser");
        // inid; //'投资信息表主键',
        // ii.setInid(2);
        if (user != null) {
            ii.setUserid(user.getUid()); // '投资用户主键',
            // ii.setBrrowid(bm.getId()); //'投标的主键',
            ii.setBrrowid(pro.getId());//
            ii.setInmoney(new BigDecimal(money)); // '投资金额',
            ii.setInstatus("不用"); // '投资状态 0 收益中的投资 1已完成的投资',
            ii.setInstyle("不用"); // '投资类型',
            // brrowstatus;// '借贷状态是否凑资完',
            ii.setBrrowstatus("不用");
            ii.setInterest(pro.getPincome()); // '投资利率',
            ii.setProfitmodel(pro.getPway()); // '盈利方式 如等额本金',
            ii.setProfitmoney(new BigDecimal("0.00")); // '盈利金额',
            Date date = new Date();
            // @SuppressWarnings("deprecation")
            // String d = date.toLocaleString();
            @SuppressWarnings("deprecation")
            Timestamp ts = new Timestamp(date.getYear(), date.getMonth(),
                    date.getDay(), date.getHours(), date.getMinutes(),
                    date.getSeconds(), 0);
            ii.setIndate(ts); // '投资时间，可为空'

            // ii.setReplaydate(Integer.parseInt(bm.getBlimit())); //
            long days = (pro.getPcount().getTime() - pro.getPtime().getTime())
                    / (24 * 60 * 60 * 1000);// 相差几天
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ds = sdf.format(pro.getPcount());

            ii.setReplaydate(ds + "(共" + days + "天)");
            ii.setMarkstatus(0); // '投标状态 0默认投标中 1 投标通过（中标） 2投标未通过（失标）';

            System.out.println(ii.toString());
            hs.removeAttribute("Product");
            hs.removeAttribute("Details");
            investService.investA(ii);//添加投资记录

            //从用户可用余额中扣除金额
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", user.getUid());
            String kym = (String) req.getSession().getAttribute("kymoney");//可用总金额
            String nkym = (Double.parseDouble(kym) - Double.parseDouble(money)) + "";//扣除投资后剩余的可用金额
            map.put("money", nkym);
            certificationService.updateM(map);
            hs.removeAttribute("kymoney");
            //写入用户账户金额记录表
            Trade td = new Trade();
            td.setuID(user.getUid());
            td.setUname(user.getUnickname());
            td.setZname(user.getUname());
            td.setJymoney(money);
            td.setOther("要投资就要舍得花钱");
            tradeService.addDate(td);
            //修改项目凑集资金
//			Product product = productService.get(pro.getId());
            Double updMoney = Double.parseDouble(pro.getPmoney() + "") + Double.parseDouble(money);
            System.out.println("修改完后的金额" + updMoney);
            pro.setPmoney(updMoney.intValue());
            productService.update(pro);
            //判断项目是否满标
            DecimalFormat df = new DecimalFormat("0.00");
            String udm = df.format(updMoney).toString();
            String odm = df.format(pro.getPtotalmoney()).toString();
            if (udm.equals(odm)) {//刚好凑集完
                pro.setPstate("2");//修改为凑资完
                productService.update(pro);
            }
            hs.setAttribute("end", "end");
        }

        return "redirect:investInfo.do?bmid=" + pro.getId();
    }

    @RequestMapping("investRecord")
    public String investRecord(Model model,
                               @RequestParam(value = "currpage", required = false) String currpage, HttpServletRequest req) {// 查出投资记录
        Users u = (Users) req.getSession().getAttribute("globaluser");

        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数

        int outcount = 0;// 不够一页的数据条数
        int count = 0;//

        Map<String, Object> parameters = new HashMap<String, Object>();// 查询条件
        if (u != null) {//用户已登录就查出此用户的数据否则所有数据
            parameters.put("uid", u.getUid());
        }
        List<InvestInfo> page = investService.investS(parameters);// 查出数据条数
        totalrow = page.size();// 获取总行数
        System.out.println("此标的投资信息记录条数" + totalrow);
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }
        // totalpage = (totalrow + pagerow - 1) / pagerow;

        outcount = totalrow % pagerow;
        count = totalrow / pagerow;

        totalpage = count;

        if (outcount > 0) {
            totalpage = count + 1;
        }

        if (currpages < 1) {
            currpages = 1;
        }
        if (currpages > totalpage) {
            currpages = totalpage;
        }
        // Integer pandc = pagerow * currpages;
        Integer candp = (currpages - 1) * pagerow;
        if (candp < 0) {
            candp = 0;
        }
        parameters.put("pandc", 5);
        parameters.put("candp", candp);
        List<InvestInfo> list = investService.investS(parameters);

        model.addAttribute("totalrow", totalrow);
        model.addAttribute("currpages", currpages);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("record", list);

        // 查出一些总额
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rowName", "inmoney");// 查出投资总额
        map.put("tableName", "investinfo");
        if (u != null) {//用户已登录就查出此用户的数据否则所有数据
            map.put("uid", u.getUid());
        }

        Double tm = investService.sumMoney(map);//查出投资总额
        model.addAttribute("tm", tm);
        System.out.println("tm" + tm);
        map.put("rowName", "profitmoney");

        Double gm = investService.sumMoney(map);// 查出收益总额
        model.addAttribute("gm", gm);
        System.out.println("gm" + gm);

        //查出退还的本金
        List<Trade> tmonery = tradeService.selectMoney(u.getUid());
        Integer allM = 0;
        for (Trade tr : tmonery) {
            String money = tr.getJymoney().replace("+", "");
            allM += Integer.parseInt(money);
        }
        System.out.println("退还本金总额" + allM);

        //查出总收益
        Integer gtm = investService.getMoney(u.getUid());
        model.addAttribute("gtm", gtm);

        Map<String, Object> bmap = new HashMap<String, Object>();
        List<Biao> biao = biaoService.findList(bmap);
        model.addAttribute("biao", biao);
        model.addAttribute("thm", allM);
        return "investrecord";
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
