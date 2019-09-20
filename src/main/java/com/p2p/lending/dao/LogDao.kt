package com.p2p.lending.dao

import com.p2p.lending.entity.Log

import org.springframework.stereotype.Repository

@Repository
interface LogDao : BaseDao<Long, Log>
