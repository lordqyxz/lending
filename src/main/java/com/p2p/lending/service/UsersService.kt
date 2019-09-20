package com.p2p.lending.service

import org.apache.ibatis.annotations.Param
import com.p2p.lending.entity.Users

interface UsersService {

    fun queryUserslimits(map: Map<String, Any>): List<Users>
    fun userList(): List<Users>
    fun insert(users: Users): Int
    fun queryUserslimits(unickname: String): List<Users>

    fun byNameFindUsers(@Param("unickname") unickname: String, @Param("upassword") upassword: String): Users
    /**
     * @author 陈庆山
     * @param map
     * @explain users 的分页查询
     */
    fun queryUser(map: Map<String, Any>): List<Users>
}
