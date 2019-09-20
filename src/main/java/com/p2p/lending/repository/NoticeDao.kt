package com.p2p.lending.repository

import com.p2p.lending.entity.Notice

/**
 *
 * @author zhouqi 2017年2月23日10:13:22 网站消息通告模块
 */
import org.springframework.stereotype.Repository

@Repository
interface NoticeDao {
    /**
     * 查询网站通知表list
     *
     * @return
     */
    fun noticelist(ids: String): List<Notice>

    /**
     * 查询网站通知表 通知详情
     *
     * @return
     */
    fun noticeget(ids: Int?): Notice

    /**
     * 添加网站通知表 通知详情
     *
     * @return
     */
    fun noticeadd(notice: Notice)

    /**
     * 修改网站通知表 通知详情
     *
     * @return
     */
    fun noticeupd(notice: Notice)


    fun noticeupds(notice: Notice)

    fun noticshiji(ids: Int?)
    /**
     * 修改网站通知表 通知详情
     *
     * @return
     */
    fun noticedel(ids: Int?)


    fun noticetop5(): List<Notice>

    fun noticetop5meiti(): List<Notice>

    fun noticetop5sy(): List<Notice>

}
