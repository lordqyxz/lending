package com.p2p.lending.dao

import com.p2p.lending.entity.Certification

interface CertificationDao {
    //查出某个用户金额
    fun selMoney(uid: Int?): String
    //修改某个用户金额
    fun updMoney(map: Map<String, Any>): Boolean

    fun upmoney(map: Map<String, Any>): Boolean
    //添加钱包数据
    fun insert(cer: Certification): Int
    //查出数据
    fun select(uid: Int?): Certification
}