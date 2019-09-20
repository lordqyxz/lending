package com.p2p.lending.dao

import com.p2p.lending.entity.Borrowcord

interface BorrowcordDao {

    fun selborr(wid: Int?): List<Borrowcord>


    fun updborr(wid: Int?)


    fun borradd(borrowcord: Borrowcord)


}
