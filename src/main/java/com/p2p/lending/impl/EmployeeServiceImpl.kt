package com.p2p.lending.impl

import com.p2p.lending.dao.EmployeeDao
import com.p2p.lending.entity.Employee
import com.p2p.lending.service.EmployeeService
import org.apache.ibatis.annotations.Param
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class EmployeeServiceImpl : EmployeeService {

    @Autowired
    var employeedao: EmployeeDao? = null

    override fun findlist(): List<Employee> {
        return employeedao!!.findlist()
    }

    override fun insert(emp: Employee): Int {
        return employeedao!!.insert(emp)
    }

    // 登录
    override fun empLogin(@Param("ename") ename: String, @Param("epassword") epassword: String): Employee? {
        println("----------------" + ename + "mima" + epassword)
        var employee: Employee? = employeedao!!.empLogin(ename, epassword)
        if (employee == null || employee.ename == "") {
            employee = null
            return employee
        } else {
            if (employee.epassword != epassword) {
                employee = null
            }
            return employee
        }
    }


    override fun add(emp: Employee): Int {
        return employeedao!!.add(emp)
    }


    override fun del(did: Int?): Int {
        return employeedao!!.del(did)
    }


    override fun toupd(did: Int?): Employee {
        return employeedao!!.toupd(did)
    }


    override fun upd(emp: Employee): Int {
        return employeedao!!.upd(emp)
    }

    override fun selectlike(ename: String): List<Employee> {
        return employeedao!!.selectlike(ename)
    }


    override fun findByName(ename: String): Employee {
        return employeedao!!.empLogin(ename, "")
    }

}
