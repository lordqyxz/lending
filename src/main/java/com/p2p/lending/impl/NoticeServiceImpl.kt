package com.p2p.lending.impl

import com.p2p.lending.repository.NoticeDao
import com.p2p.lending.entity.Notice
import com.p2p.lending.service.NoticeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

/**
 *
 * @author 周旗 网站消息通告 控制层
 */
@Service
@Transactional
open class NoticeServiceImpl : NoticeService {

    @Resource
    private val noticeDao: NoticeDao? = null

    override fun noticelist(ids: String): List<Notice> {
        // TODO Auto-generated method stub
        return noticeDao!!.noticelist(ids)
    }

    override fun noticeget(ids: Int?): Notice {

        return noticeDao!!.noticeget(ids)
    }

    // ______________________________
    override fun noticetop5(): List<Notice> {
        // TODO 自动生成的方法存根
        return noticeDao!!.noticetop5()
    }

    override fun noticetop5meiti(): List<Notice> {
        // TODO 自动生成的方法存根
        return noticeDao!!.noticetop5meiti()
    }

    override fun noticetop5sy(): List<Notice> {
        // TODO 自动生成的方法存根
        return noticeDao!!.noticetop5sy()
    }

    // ______________________________

    override fun noticeadd(notice: Notice) {
        noticeDao!!.noticeadd(notice)

    }

    override fun noticeupd(notice: Notice) {
        noticeDao!!.noticeupd(notice)

    }

    override fun noticedel(ids: Int?) {
        noticeDao!!.noticedel(ids)
    }

    override fun noticeupds(notice: Notice) {
        // TODO 自动生成的方法存根
        noticeDao!!.noticeupd(notice)

    }

    override fun noticshiji(ids: Int?) {
        noticeDao!!.noticshiji(ids)
    }

}
