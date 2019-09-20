package com.p2p.lending.service

import com.p2p.lending.entity.Clapplyfor
import org.springframework.stereotype.Service

@Service
interface ClapplyforService {
    fun queryClapplyfors(map: Map<String, Any?>): List<Clapplyfor>

    fun insertClapplyfor(map: Map<String, Any?>): Int

    fun updateClapplyforState(map: Map<String, Any?>): Int
}
