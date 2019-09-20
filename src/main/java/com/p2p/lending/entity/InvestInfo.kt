package com.p2p.lending.entity

import java.math.BigDecimal
import java.sql.Timestamp

class InvestInfo : Serializable() {

    var inid: Int = 0 // '投资信息表主键',
    var userid: Int = 0 // '投资用户主键',
    var brrowid: Int = 0 // '投标的主键',
    var inmoney: BigDecimal? = null // '投资金额',
    var instatus: String? = null // '投资状态 0 收益中的投资 1已完成的投资',
    var instyle: String? = null // '投资类型',
    var brrowstatus: String? = null// '借贷状态是否凑资完',
    var interest: String? = null // '投资利率',
    var profitmodel: String? = null // '盈利方式 如等额本金',
    var profitmoney: BigDecimal? = null // '盈利金额',
    var indate: Timestamp? = null // '投资时间，可为空'

    var replaydate: String? = null //
    var markstatus: Int = 0 // '投标状态 0默认投标中 1 投标通过（中标） 2投标未通过（失标）';

    var title: String? = null
    var type: String? = null
    var uname: String? = null
    var startPage: Int? = null
    var pageSize: Int? = null

    override fun toString(): String {
        return ("InvestInfo [inid=" + inid + ", userid=" + userid + ", brrowid=" + brrowid + ", inmoney=" + inmoney
                + ", instatus=" + instatus + ", instyle=" + instyle + ", brrowstatus=" + brrowstatus + ", interest="
                + interest + ", profitmodel=" + profitmodel + ", profitmoney=" + profitmoney + ", indate=" + indate
                + ", replaydate=" + replaydate + ", markstatus=" + markstatus + "]")
    }

    companion object {

        /**
         *
         */
        private val serialVersionUID = 1L
    }
}
