package com.p2p.lending.repository

import com.p2p.lending.entity.Borrowcord

import org.springframework.stereotype.Repository

@Repository
interface BorrowcordDao {

    fun selborr(wid: Int?): List<Borrowcord>


    fun updborr(wid: Int?)


    fun borradd(borrowcord: Borrowcord)


}
