package com.p2p.lending.dao

import com.p2p.lending.entity.Clapplyfor

interface ClapplyforDao {

    fun queryClapplyfors(map: Map<String, Any>): List<Clapplyfor>

    fun insertClapplyfor(map: Map<String, Any>): Int

    fun updateClapplyforState(map: Map<String, Any>): Int

}
