package com.p2p.lending.service

import com.p2p.lending.entity.Users
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Service

@Service
interface UsersService {

    fun queryUserslimits(map: Map<String, Any>): List<Users>
    fun userList(): List<Users>
    fun insert(users: Users): Int
    fun queryUserslimits(unickname: String): List<Users>

    fun byNameFindUsers(@Param("unickname") unickname: String, @Param("upassword") upassword: String): Users?
    /**
     * @author 陈庆山
     * @param map
     * @explain users 的分页查询
     */
    fun queryUser(map: Map<String, Any>): List<Users>
}
