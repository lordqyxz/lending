package com.p2p.lending.impl

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.p2p.lending.entity.Borrowmoney
import com.p2p.lending.repository.BorrowmoneyDao
import com.p2p.lending.service.BorrowmoneyService
import com.p2p.lending.util.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class BorrowmoneyServiceImpl : BorrowmoneyService {

    @Autowired
    internal var borrowmoneyDao: BorrowmoneyDao? = null

    override fun get(id: Int?): Borrowmoney {
        // TODO Auto-generated method stub
        return borrowmoneyDao!![id!!]
    }

    override fun delete(id: Int?): Int {
        // TODO Auto-generated method stub
        return borrowmoneyDao!!.delete(id!!)
    }

    override fun create(record: Borrowmoney): Int {
        // TODO Auto-generated method stub
        return borrowmoneyDao!!.create(record)
    }

    override fun update(record: Borrowmoney): Int {
        // TODO Auto-generated method stub
        return borrowmoneyDao!!.update(record)
    }

    override fun findList(params: Borrowmoney, pageNo: Int?, pageSize: Int?): PageInfo<Borrowmoney> {
        var pageNo = pageNo
        var pageSize = pageSize
        pageNo = pageNo ?: 1
        pageSize = pageSize ?: 3
        PageHelper.startPage<Any>(pageNo, pageSize)
        val list = borrowmoneyDao!!.findList<Any>(BeanUtils.toMap(params)!!)

        val page = PageInfo(list)
        println(" PageNum " + page.pageNum)
        println("PageSize " + page.pageSize)
        println("StartRow " + page.startRow)
        println("EndRow " + page.endRow)
        println("Total " + page.total)
        println("Pages " + page.pages)
        println("FirstPages  " + page.firstPage)
        println("LastPage " + page.lastPage)
        println("isHasPreviousPage " + page.isHasPreviousPage)
        println("HasNextPage " + page.isHasNextPage)

        return page
    }

    override fun findList(map: Map<String, Any>): List<Borrowmoney> {
        return borrowmoneyDao!!.findList(map)
    }

    /*
	 *
	 * @ygx 2017 2 25
	 */
    override fun pagingSel(map: Map<String, Any>): List<Borrowmoney> {
        return borrowmoneyDao!!.pagingList(map)
    }

    // 周旗 2017年3月3日10:41:28
    override fun toaddborr(borrowmoney: Borrowmoney) {
        borrowmoneyDao!!.toaddborr(borrowmoney)
    }

    override fun updhuankuan(): List<Borrowmoney> {
        // TODO Auto-generated method stub
        return borrowmoneyDao!!.updhuankuan()
    }

    override// 获取方法一条详细信息
    fun borrowget(ids: Int?): Borrowmoney {
        return borrowmoneyDao!!.borrowget(ids)
    }

    /**
     * hjy
     */
    override fun selecthjy(currpage: String): Map<String, Any> {
        val map = HashMap<String, Any>()
        val m = HashMap<String, Any>()
        val pagerow = 5// 每页5行
        var currpages = 1// 当前页
        var totalpage = 0// 总页数
        val totalrow = borrowmoneyDao!!.hjycount()// 总行数
        totalpage = (totalrow + pagerow - 1) / pagerow
        if (currpage != null && "" != currpage) {
            currpages = Integer.parseInt(currpage)
        }
        if (currpages < 1) {
            currpages = 1
        }
        if (currpages > totalpage) {
            currpages = totalpage
        }
        val l1 = (currpages - 1) * pagerow
        m["l1"] = l1
        m["l2"] = pagerow

        val llist = borrowmoneyDao!!.selecthjy(m)
        map["pagerow"] = pagerow
        map["currpages"] = currpages
        map["llist"] = llist
        map["totalpage"] = totalpage
        map["totalrow"] = totalrow
        return map
    }

}
