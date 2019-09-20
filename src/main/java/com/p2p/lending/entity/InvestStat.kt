package com.p2p.lending.entity

import java.math.BigDecimal
import java.sql.Timestamp

class InvestStat : BaseDomain() {

    var isid: Int = 0// '投资信息统计表主键',
    var userid: Int = 0// '投资用户主键',
    var markcount: Int = 0// '总投标数',
    var lmcount: Int = 0// '失标总数',
    var gmcount: Int = 0// '中标总数',
    var markmoney: BigDecimal? = null// '投资总金额',
    var markgain: BigDecimal? = null// '投资总收益',
    var markbegindate: Timestamp? = null// '投资起始时间点',
    var markenddate: Timestamp? = null// '投资结束时间点'

    override fun toString(): String {
        return ("InvestStat [isid=" + isid + ", userid=" + userid + ", markcount=" + markcount + ", lmcount=" + lmcount
                + ", gmcount=" + gmcount + ", markmoney=" + markmoney + ", markgain=" + markgain + ", markbegindate="
                + markbegindate + ", markenddate=" + markenddate + "]")
    }

    companion object {
        /**
         *
         */
        private val serialVersionUID = 1L
    }

}
