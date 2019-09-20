package com.p2p.lending.controller

import com.p2p.lending.entity.Employee
import com.p2p.lending.entity.Withdrawal
import com.p2p.lending.service.WithdrawalService
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.io.FileOutputStream
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.swing.JFileChooser

@Controller
@RequestMapping("wd")
class WithdrawalController {
    internal var str = "WEB-INF/view/"
    @Autowired
    private val withdrawalService: WithdrawalService? = null

    @RequestMapping("wlist")
    @Throws(UnsupportedEncodingException::class)
    fun withdrawallist(
            model: Model,
            @RequestParam(value = "currpage", required = false) currpage: String,
            @RequestParam(value = "btn", required = false) btn: String,
            httpServletRequest: HttpServletRequest,
            @RequestParam(value = "wname", required = false) wname: String,
            @RequestParam(value = "yyy", required = false) yyy: String,
            @RequestParam(value = "yyyy", required = false) yyyy: String,
            @RequestParam(value = "wstatu", required = false) wstatu: String): String {
        val httpSession = httpServletRequest.session
        httpSession.setAttribute("btn", btn)

        val findmap = HashMap<String, Any>()
        findmap["wname"] = wname
        findmap["yyy"] = yyy
        findmap["yyyy"] = yyyy
        findmap["wstatu"] = wstatu
        httpServletRequest.characterEncoding = "utf-8"

        httpSession.setAttribute("wname", wname)
        httpSession.setAttribute("yyy", yyy)
        httpSession.setAttribute("yyyy", yyyy)
        httpSession.setAttribute("wstatu", wstatu)

        val withdrawallist = withdrawalService!!.withdrawallist(currpage, btn, findmap)
        val llist = withdrawallist["llist"] as List<Withdrawal>
        model.addAttribute("pagerow", withdrawallist["pagerow"])
        model.addAttribute("currpages", withdrawallist["currpages"])
        model.addAttribute("wdlist", llist)
        model.addAttribute("totalpage", withdrawallist["totalpage"])
        model.addAttribute("totalrow", withdrawallist["totalrow"])
        val suntxmoney = withdrawalService.sumtxmoney()
        model.addAttribute("suntxmoney", suntxmoney)
        val sumdzmoney = withdrawalService.sumdzmoney()
        model.addAttribute("sumdzmoney", sumdzmoney)
        val sumsxf = withdrawalService.sumsxf()
        model.addAttribute("sumsxf", sumsxf)
        return str + "Withdrawallist"
    }

    //ajax
    @RequestMapping("ajax")
    @ResponseBody
    fun ajax(@RequestParam(value = "id", required = false) id: Int): Withdrawal {
        println(id)
        println(withdrawalService!!.selectone(id).uname)
        return withdrawalService.selectone(id)
    }

    //转账成功或失败
    @RequestMapping("zhuans")
    fun zhuan(@RequestParam(value = "gg", required = false) gg: Int,
              @RequestParam(value = "wid", required = false) wid: Int): String {
        val wone = withdrawalService!!.selectone(wid)
        if (gg == 0) {
            //失败
            withdrawalService.updwith(0, wid)
            //退钱

            val txmoney = Integer.parseInt(wone.txmoney!!)//体检金额
            val uid = wone.getuID()//用户id
            withdrawalService.updmoney(txmoney, uid!!)
            val i = 1
            //添加失败的交易记录
            withdrawalService.intmoney(wone, i)
        } else if (gg == 1) {
            //成功
            withdrawalService.updwith(gg, wid)
            val i = 2
            //添加交易成功记录
            withdrawalService.intmoney(wone, i)
        }
        return "redirect:wlist.do"
    }

    //审核通过
    @RequestMapping("shen")
    fun shen(@RequestParam(value = "gg", required = false) gg: Int,
             @RequestParam(value = "wid", required = false) wid: Int, req: HttpServletRequest): String {
        val session = req.session
        val emp = session.getAttribute("globalemp") as Employee
        val shname = emp.ename
        if (gg == 0) {
            //失败 需要改成失败  并且修改转账时间，审核人时间，审核人
            withdrawalService!!.updwiths(gg, wid, shname!!)
            //退钱
            val wone = withdrawalService.selectone(wid)
            val txmoney = Integer.parseInt(wone.txmoney!!)//体检金额
            val uid = wone.getuID()//用户id
            withdrawalService.updmoney(txmoney, uid!!)
            val i = 0
            //添加失败的交易记录
            withdrawalService.intmoney(wone, i)
        } else if (gg == 2) {
            //成功    需要改成转账中  并且修改转账时间，审核人时间，审核人

            withdrawalService!!.updwiths(gg, wid, shname!!)

        }
        return "redirect:wlist.do"
    }

    /**
     * 导出excel
     *
     * @throws IOException
     */
    @RequestMapping("putexcel")
    @Throws(IOException::class)
    fun putexcel(httpServletResponse: HttpServletResponse): String {
        val workBook = HSSFWorkbook()
        val sheet = workBook.createSheet("提现管理")
        val titleRow = sheet.createRow(0)
        // 标题行
        val cell1 = titleRow.createCell(0)
        cell1.setCellValue("用户ID")
        val cell2 = titleRow.createCell(1)
        cell2.setCellValue("用户名")
        val cell3 = titleRow.createCell(2)
        cell3.setCellValue("真实姓名")
        val cell4 = titleRow.createCell(3)
        cell4.setCellValue("账户")
        val cell5 = titleRow.createCell(4)
        cell5.setCellValue("提现银行")
        val cell6 = titleRow.createCell(5)
        cell6.setCellValue("提现金额")
        val cell7 = titleRow.createCell(6)
        cell7.setCellValue("到账金额")
        val cell8 = titleRow.createCell(7)
        cell8.setCellValue("手续费")
        val cell9 = titleRow.createCell(8)
        cell9.setCellValue("提现时间")
        val cell10 = titleRow.createCell(9)
        cell10.setCellValue("转账时间")
        val cell11 = titleRow.createCell(10)
        cell11.setCellValue("状态0失败，1已提现,2转账中，3审核中，）")
        val cell12 = titleRow.createCell(11)
        cell12.setCellValue("审核人")
        val cell13 = titleRow.createCell(12)
        cell13.setCellValue("备注")

        val lw = withdrawalService!!.selectallw()
        for (i in lw.indices) {
            val wi = lw[i]
            // 数据行
            val dataRow = sheet.createRow(i + 1)
            val uid = dataRow.createCell(0)
            uid.setCellValue(wi.getuID()!!.toDouble())
            val uname = dataRow.createCell(1)
            uname.setCellValue(wi.uname)
            val zname = dataRow.createCell(2)
            zname.setCellValue(wi.zname)
            val txnum = dataRow.createCell(3)
            txnum.setCellValue(wi.txnum)
            val txbank = dataRow.createCell(4)
            txbank.setCellValue(wi.txbank)
            val txmoney = dataRow.createCell(5)
            txmoney.setCellValue(wi.txmoney)
            val dzmoney = dataRow.createCell(6)
            dzmoney.setCellValue(wi.dzmoney)
            val sxf = dataRow.createCell(7)
            sxf.setCellValue(wi.sxf)
            val txtime = dataRow.createCell(8)

            val dateStyle = workBook.createCellStyle()
            val dateFormat = workBook.createDataFormat()
            dateStyle.dataFormat = dateFormat.getFormat("yyyy-MM-dd HH:mm:ss")
            txtime.setCellStyle(dateStyle)

            txtime.setCellValue(wi.txtime)
            val zztime = dataRow.createCell(9)

            zztime.setCellStyle(dateStyle)

            zztime.setCellValue(wi.zztime)
            val statu = dataRow.createCell(10)
            statu.setCellValue(wi.statu)
            val shwho = dataRow.createCell(11)
            shwho.setCellValue(wi.shwho)
            val nothing = dataRow.createCell(12)
            nothing.setCellValue(wi.nothing)
        }

        val chooser = JFileChooser()
        chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        chooser.showOpenDialog(null)
        val path = chooser.selectedFile.path

        if (path != null && path != "") {

            val fos = FileOutputStream(
                    "$path\\提现信息.xls")
            workBook.write(fos)
        }

        return "redirect:wlist.do"
    }
}
