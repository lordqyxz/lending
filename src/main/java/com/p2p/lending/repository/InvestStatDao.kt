package com.p2p.lending.repository

import com.p2p.lending.entity.InvestStat

import org.springframework.stereotype.Repository

@Repository
interface InvestStatDao : BaseDao<Any, InvestStat>
