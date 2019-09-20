package com.p2p.lending.repository

import com.p2p.lending.entity.Clapplyfor

import org.springframework.stereotype.Repository

@Repository
interface ClapplyforDao {

    fun queryClapplyfors(map: Map<String, Any>): List<Clapplyfor>

    fun insertClapplyfor(map: Map<String, Any>): Int

    fun updateClapplyforState(map: Map<String, Any>): Int

}
