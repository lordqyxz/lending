package com.p2p.lending.service

import com.p2p.lending.entity.Borrowcord

interface BorrowcordService {
    fun selborr(wid: Int?): List<Borrowcord>

    fun updborr(wid: Int?)

    fun borradd(yss: String, beyid: Int?, fangshi: String)
}
