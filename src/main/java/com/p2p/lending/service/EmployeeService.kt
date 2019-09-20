package com.p2p.lending.service

import org.apache.ibatis.annotations.Param
import com.p2p.lending.entity.Employee

interface EmployeeService {

    // 无条件查询所有加分页
    fun findlist(): List<Employee>

    // 添加新员工
    fun insert(emp: Employee): Int

    // 登录
    fun empLogin(@Param("ename") ename: String, @Param("epassword") epassword: String): Employee

    //添加员工
    fun add(emp: Employee): Int

    fun del(did: Int?): Int
    fun toupd(did: Int?): Employee
    fun upd(emp: Employee): Int
    fun selectlike(ename: String): List<Employee>
    fun findByName(ename: String): Employee
}
