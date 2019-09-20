package com.p2p.lending.controller;

import com.p2p.lending.entity.*;
import com.p2p.lending.service.CertificationService;
import com.p2p.lending.service.DopeService;
import com.p2p.lending.service.InformationService;
import com.p2p.lending.service.PoundageService;
import com.p2p.lending.util.BeanUtils;
import com.p2p.lending.util.CreateRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InformationController {
    @Autowired
    private InformationService informationService;
    @Autowired
    private PoundageService poundageService;
    @Autowired
    private CertificationService certificationService;
    @Autowired
    private DopeService dopeService;

    // 我的账户
    @RequestMapping("query")
    public String query(@RequestParam(value = "id", required = false) String id, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Users user = informationService.query(map);
        model.addAttribute("user", user);
        return "personalpage";
    }

    // 账户信息查询
    @RequestMapping("find")
    public String find(@RequestParam(value = "id", required = false) String id, Model model,
                       HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Users user = informationService.find(map);
        List<Approveitem> list = informationService.appquery();
        request.setAttribute("num", user.getUphonenumber());
        request.setAttribute("mailbox", user.getUmailbox());
        Approveitem app = list.get(0);
        model.addAttribute("list", app);
        model.addAttribute("user", user);

        return "account";
    }

    // 添加身份信息和认证信息
    @RequestMapping("insertUsercre")
    public String insert(@RequestParam(value = "uid", required = false) Integer uid,
                         @RequestParam(value = "unickname", required = false) String unickname,
                         @RequestParam(value = "aiid", required = false) Integer aiid,
                         @RequestParam(value = "ainame", required = false) String ainame,
                         @RequestParam(value = "realname", required = false) String realname,
                         @RequestParam(value = "idnumbers", required = false) String IDnumber, Certifrecord cer) {
        System.out.println(
                "\t" + uid + "\t" + unickname + "\t" + aiid + "\t" + ainame + "\t" + realname + "\t" + IDnumber);
        Map<String, Object> map = new HashMap<>();
        map.put("uname", realname);
        map.put("ucardid", IDnumber);
        map.put("uid", uid);
        informationService.addUsers(map);
        cer.setCruserid(uid);
        cer.setCrusername(unickname);
        cer.setCraiid(aiid);
        cer.setCrainame(ainame);
        informationService.addcertifrecord(cer);
        return "redirect:/find.do?id=" + uid;
    }

    //模拟添加第三方身份信息
    @RequestMapping("insertucertnum")
    public String insertucertnumber(@RequestParam(value = "id", required = false) String id,
                                    @RequestParam(value = "uname", required = false) String uname,
                                    @RequestParam(value = "ucardid", required = false) String ucardid,
                                    @RequestParam(value = "umailbox", required = false) String umailbox,
                                    @RequestParam(value = "uphonenumber", required = false) String uphonenumber,
                                    @RequestParam(value = "upwd_zd", required = false) String upwd_zd) {
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", id);
        int ucertnumber = (int) ((Math.random() * 9 + 1) * 100000);
        String s = String.valueOf(ucertnumber);
        stringObjectHashMap.put("ucertnumber", s);
        stringObjectHashMap.put("uname", uname);
        stringObjectHashMap.put("ucardid", ucardid);
        stringObjectHashMap.put("umailbox", umailbox);
        stringObjectHashMap.put("uphonenumber", uphonenumber);
        stringObjectHashMap.put("upwd_zd", upwd_zd);
        informationService.upucertnum(stringObjectHashMap);
        return "redirect:/find.do?id=" + id;
    }

    //获取金额，提现
    @RequestMapping("withdraw")
    public String withdraw(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("cer", certificationService.select(id));
        return "Withdraw";
    }

    //获取金额，提现
    @RequestMapping("withdrawpay")
    public String withdrawpay(@RequestParam(value = "id", required = true) String id,
                              @RequestParam(value = "actualMoney", required = true) String actualMoney) {
        Certification certi = certificationService.select(Integer.parseInt(id));
        String mnum = certi.getCbalance();
        String znum = certi.getCtotalmoney();
        float mf = Float.parseFloat(mnum);
        float af = Float.parseFloat(actualMoney);
        float afb = Float.parseFloat(znum);
        float amf = mf - af;
        float bmf = afb - af;
        Map<String, Object> map = new HashMap<>();
        map.put("uid", id);
        map.put("cbalance", String.valueOf(amf));
        map.put("ctotalmoney", String.valueOf(bmf));
        certificationService.upm(map);

        return "redirect:/withdraw.do?id=" + Integer.parseInt(id);
    }


    // 修改用户密码
    @RequestMapping("updpassword")
    public String updpassword(@RequestParam(value = "id", required = false) String id,
                              @RequestParam(value = "updatePassForm:repassword", required = false) String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        informationService.updPassword(map);
        return "redirect:/find.do?id=" + id;
    }

    //修改手机号码
    @RequestMapping("updphone")
    public String updphone(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "newPhone", required = false) String newPhone) {
        System.out.println("用户id:" + id + "新手机号:" + newPhone);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("phone", newPhone);
        informationService.updphone(map);
        return "redirect:/find.do?id=" + id;
    }

    //获取验证码
    @RequestMapping("identifying")
    @ResponseBody
    public int identifying() {
        int i = CreateRandom.random();
        try {
            System.out.println("-------");
            System.out.println("随机数" + i);
            FileWriter fileWriter;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = new Date();
            fileWriter = new FileWriter("E://test.txt");
            fileWriter.write("发送时间:" + format.format(date) + "验证码:" + i);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
    }

    //用户充值
    @RequestMapping("userpay")
    @ResponseBody
    @Transactional
    public String userpay(Poundage po) {
        String code = "200";
        Date date = new Date();
        Map<String, Object> usermap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        usermap.put("id", po.getuID());
        Users user = informationService.find(usermap);
        po.setUname(user.getUnickname());
        po.setZname(user.getUname());
        po.setWhat("充值");
        po.setSxtime(date);
        po.setBookaccount(user.getUid() + "");
        po.setPaytype("快捷支付");
        Certification certi = certificationService.select(po.getuID());
        //可用余额
        String cbal = certi.getCbalance();
        String xmoney = po.getSxmoney();
        Float fmoney = Float.valueOf(cbal) + Float.valueOf(xmoney);
        //总余额
        String moneyString = certi.getCtotalmoney();
        Float money = Float.valueOf(moneyString) + Float.valueOf(xmoney);
        map.put("id", po.getuID());
        map.put("cbalance", fmoney.toString());
        map.put("ctotalmoney", money.toString());
        Dope dope = new Dope();
        dope.setDprimkey(po.getuID());
        dope.setDtitle("充值成功");
        dope.setDetails("尊敬的" + user.getUnickname() + ",您通过" + po.getPaytype() + "充值的" + po.getSxmoney() + "元已到账!");
        dope.setDtime(date);
        //增加充值明细表数据
        poundageService.insert(po);
        //增加账户金额数据
        certificationService.undate(map);
        //添加广播数据
        dopeService.insert(dope);
        return code;
    }

    //用户资金记录
    @RequestMapping("listpay")
    public String listpay(Model model, @RequestParam(value = "currpage", required = false) String currpage, @RequestParam(value = "id", required = false) String id) {
        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数
        Poundage poundage = new Poundage();
        List<Poundage> list = poundageService.findList(BeanUtils.INSTANCE.toMap(poundage));
        totalrow = list.size();// 获取总行数
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }
        totalpage = (totalrow + pagerow - 1) / pagerow;
        if (currpages < 1) {
            currpages = 1;
        }
        if (currpages > totalpage) {
            if (totalpage < 1) {
                totalpage = 1;
            }
            currpages = totalpage;
        }
        Integer startPage = (currpages - 1) * pagerow;
        return null;
    }

}