package com.p2p.lending.dao

import com.p2p.lending.entity.Limi

import org.springframework.stereotype.Repository

@Repository
interface LimitDao {

    //根据员工ID查询该用户权限的模块
    fun limitByeid(eid: Int): List<*>

    //删除
    fun limitdel(eid: Int?): Boolean

    //添加
    fun limitadd(limi: Limi): Boolean


}
