package com.p2p.lending.repository

import com.p2p.lending.entity.Approveitem
import com.p2p.lending.entity.Certifrecord
import com.p2p.lending.entity.Users

import org.springframework.stereotype.Repository

@Repository
interface InfromationDao {
    // 我的账户
    fun query(map: Map<String, Any?>): Users

    // 账户设置
    fun find(map: Map<String, Any>): Users

    // 查询认证表
    fun appquery(): List<Approveitem>

    // 添加身份证信息
    fun addUsers(map: Map<String, Any>): Int

    // 添加认证信息到认证信息表
    fun addcertifrecord(cer: Certifrecord): Int

    //模拟添加第三方身份信息
    fun upucertnum(map: Map<String, Any>): Int

    // 修改密码
    fun updPassword(map: Map<String, Any>): Int

    //修改手机号码
    fun updphone(map: Map<String, Any>): Int
}
