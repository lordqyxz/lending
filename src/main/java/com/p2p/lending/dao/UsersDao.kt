package com.p2p.lending.dao

import org.apache.ibatis.annotations.Param
import com.p2p.lending.entity.Users

interface UsersDao {

    fun queryUserslimits(map: Map<String, Any>): List<Users>

    fun queryUserslimitss(@Param("unickname") unickname: String): List<Users>

    fun userList(): List<Users>

    fun insert(users: Users): Int

    fun byNameFindUsers(@Param("unickname") unickname: String, @Param("upassword") upassword: String): Users
    /**
     *
     * @param map
     * @explain users 的分页查询
     */
    fun queryUser(map: Map<String, Any>): List<Users>
}
