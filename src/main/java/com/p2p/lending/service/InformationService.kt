package com.p2p.lending.service

import com.p2p.lending.entity.Approveitem
import com.p2p.lending.entity.Certifrecord
import com.p2p.lending.entity.Users
import org.springframework.stereotype.Service

@Service
interface InformationService {
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

    // 根据id查询密码是否输入正确
    fun updPassword(map: Map<String, Any>): Int

    //修改手机号码
    fun updphone(map: Map<String, Any>): Int

    //充值金额
    fun userpay(map: Map<String, Any>): Int
}
