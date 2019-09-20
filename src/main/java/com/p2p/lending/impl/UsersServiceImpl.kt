package com.p2p.lending.impl

import com.p2p.lending.repository.UsersDao
import com.p2p.lending.entity.Users
import com.p2p.lending.service.UsersService
import org.apache.ibatis.annotations.Param
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class UsersServiceImpl : UsersService {

    @Autowired
    private val usersdao: UsersDao? = null


    override fun insert(users: Users): Int {
        return usersdao!!.insert(users)
    }

    // 登录
    override fun byNameFindUsers(@Param("unickname") unickname: String, @Param("upassword") upassword: String): Users? {
        // 根据用户名查询
        var users: Users? = usersdao!!.byNameFindUsers(unickname, upassword)
        if (users == null || users.equals("")) {
            // 则输入用户不存在
            users = null
            return users
        } else {
            // 用户存在，检验密码
            if (users.upassword != upassword) {
                users = null
            }
            return users
        }

    }


    override fun queryUserslimits(map: Map<String, Any>): List<Users> {
        return usersdao!!.queryUserslimits(map)
    }


    override fun userList(): List<Users> {
        return usersdao!!.userList()
    }

    /**
     * @author 陈庆山
     * @param map
     * @explain users 的分页查询
     */

    override fun queryUser(map: Map<String, Any>): List<Users> {
        // TODO Auto-generated method stub
        return usersdao!!.queryUser(map)
    }


    override fun queryUserslimits(
            unickname: String): List<Users> {
        return usersdao!!.queryUserslimitss(unickname)
    }

}
