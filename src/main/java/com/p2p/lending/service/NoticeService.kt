package com.p2p.lending.service

import com.p2p.lending.entity.Notice
import org.springframework.stereotype.Service

@Service
interface NoticeService {

    fun noticelist(ids: String): List<Notice>

    fun noticeget(ids: Int?): Notice

    fun noticeadd(notice: Notice)

    fun noticeupd(notice: Notice)

    fun noticedel(ids: Int?)

    fun noticeupds(notice: Notice)

    fun noticshiji(ids: Int?)

    fun noticetop5(): List<Notice>
    fun noticetop5meiti(): List<Notice>
    fun noticetop5sy(): List<Notice>


}
