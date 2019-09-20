package com.p2p.lending.controller

import com.p2p.lending.entity.Recharge
import com.p2p.lending.service.RechargeService
import org.apache.poi.hssf.usermodel.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import javax.swing.*
import java.io.FileOutputStream
import java.io.IOException
import java.util.HashMap

@Controller
@RequestMapping("rc")
class RechargeController {
    internal var str = "WEB-INF/view/"
    @Autowired
    private val rechargeService: RechargeService? = null


    @RequestMapping("rech")
    fun rech(model: Model,
             @RequestParam(value = "currpage", required = false) currpage: String,
             @RequestParam(value = "uname", required = false) uname: String,
             @RequestParam(value = "yyy", required = false) yyy: String,
             @RequestParam(value = "yyyy", required = false) yyyy: String,
             @RequestParam(value = "zflx", required = false) zflx: String,
             @RequestParam(value = "statu", required = false) statu: String,
             httpServletRequest: HttpServletRequest): String {
        val session = httpServletRequest.session
        session.setAttribute("uname", uname)
        session.setAttribute("yyy", yyy)
        session.setAttribute("yyyy", yyyy)
        session.setAttribute("statu", statu)
        session.setAttribute("zflx", zflx)

        val findmap = HashMap<String, Any>()
        findmap["uname"] = uname
        findmap["yyy"] = yyy
        findmap["yyyy"] = yyyy
        findmap["statu"] = statu
        findmap["zflx"] = zflx


        val map = rechargeService!!.selectrc(currpage, findmap)

        model.addAttribute("pagerow", map["pagerow"])
        model.addAttribute("currpages", map["currpages"])
        model.addAttribute("lrc", map["lrc"])
        model.addAttribute("totalpage", map["totalpage"])
        model.addAttribute("totalrow", map["totalrow"])
        model.addAttribute("czmoneyre", rechargeService.sumczmoneyre())
        model.addAttribute("dzmoneyre", rechargeService.sumdzmoneyre())
        return str + "Rechargelist"
    }

    /**
     * 导出excel
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("putexcelr")
    @Throws(IOException::class)
    fun putexcelr(): String {
        val workBook = HSSFWorkbook()
        val sheet = workBook.createSheet("充值记录")
        val titleRow = sheet.createRow(0)
        // 标题行
        val cell1 = titleRow.createCell(0)
        cell1.setCellValue("用户ID")
        val cell2 = titleRow.createCell(1)
        cell2.setCellValue("用户名")
        val cell3 = titleRow.createCell(2)
        cell3.setCellValue("真实名")
        val cell4 = titleRow.createCell(3)
        cell4.setCellValue("充值类型")
        val cell5 = titleRow.createCell(4)
        cell5.setCellValue("流水号")
        val cell6 = titleRow.createCell(5)
        cell6.setCellValue("充值金额")
        val cell7 = titleRow.createCell(6)
        cell7.setCellValue("费率")
        val cell8 = titleRow.createCell(7)
        cell8.setCellValue("到账金额")
        val cell9 = titleRow.createCell(8)
        cell9.setCellValue("转账时间")
        val cell10 = titleRow.createCell(9)
        cell10.setCellValue("状态")

        val lw = rechargeService!!.selectall()
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
            val czlx = dataRow.createCell(3)
            czlx.setCellValue(wi.czlx)
            val lsh = dataRow.createCell(4)
            lsh.setCellValue(wi.lsh)
            val czmoney = dataRow.createCell(5)
            czmoney.setCellValue(wi.czmoney)
            val fl = dataRow.createCell(6)
            fl.setCellValue(wi.fl)
            val dzmoney = dataRow.createCell(7)
            dzmoney.setCellValue(wi.dzmoney)
            val cztime = dataRow.createCell(8)

            val dateStyle = workBook.createCellStyle()
            val dateFormat = workBook.createDataFormat()
            dateStyle.dataFormat = dateFormat.getFormat("yyyy-MM-dd HH:mm:ss")
            cztime.setCellStyle(dateStyle)

            cztime.setCellValue(wi.cztime)
            val statu = dataRow.createCell(9)
            statu.setCellValue(wi.statu)

        }

        val chooser = JFileChooser()
        chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        chooser.showOpenDialog(null)
        val path = chooser.selectedFile.path

        if (path != null && path != "") {

            val fos = FileOutputStream(
                    "$path\\充值记录.xls")
            workBook.write(fos)
        }
        return "redirect:rech.do"
    }
}
