package com.p2p.lending.repository

import com.p2p.lending.entity.Employee
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
interface EmployeeDao {

    // 无条件查询所有加分页
    fun findlist(): List<Employee>

    // 添加新员工
    fun insert(emp: Employee): Int

    // 登录
    fun empLogin(@Param("ename") ename: String, @Param("epassword") epassword: String): Employee

    //添加员工
    fun add(emp: Employee): Int

    //删除
    fun del(did: Int?): Int

    fun toupd(did: Int?): Employee

    fun upd(emp: Employee): Int

    //模糊查找
    fun selectlike(ename: String): List<Employee>


}
