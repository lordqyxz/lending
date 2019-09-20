package com.p2p.lending.impl

import com.p2p.lending.dao.BorrowcordDao
import com.p2p.lending.entity.Borrowcord
import com.p2p.lending.service.BorrowcordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class BorrowcordServiceImpl : BorrowcordService {

    @Autowired
    private val borrowcordDao: BorrowcordDao? = null

    override
            /** 根据借款表ID查询还款记录  */
    fun selborr(wid: Int?): List<Borrowcord> {
        return borrowcordDao!!.selborr(wid)
    }

    override fun updborr(wid: Int?) {
        borrowcordDao!!.updborr(wid)
    }

    /**处理还款记录表 */
    override fun borradd(yss: String, beyid: Int?, fangshi: String) {

        // 新建还款表数据(还款限制月数)
        val ys = Integer.parseInt(yss)

        var date = Date()

        val borrowcord = Borrowcord()
        borrowcord.bstatue = 0// 设置还款记录表状态
        borrowcord.bid = beyid// 设置借款表ID

        val calendar = Calendar.getInstance()// 时间转换
        if (fangshi !== "1") {
            for (i in 0 until ys) {
                calendar.time = date
                calendar.add(Calendar.SECOND, 60 * 60 * 24 * 30)
                date = calendar.time
                borrowcord.bdate = date
                borrowcord.bcs = i + 1
                borrowcordDao!!.borradd(borrowcord)
            }
        } else {
            calendar.time = date
            calendar.add(Calendar.SECOND, 1000 * 60 * 60 * 24 * 30 * ys)
            date = calendar.time
            borrowcord.bdate = date
            borrowcord.bcs = 1
            borrowcordDao!!.borradd(borrowcord)
        }
    }

}
