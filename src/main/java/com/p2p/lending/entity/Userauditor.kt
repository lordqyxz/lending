package com.p2p.lending.entity

import java.io.Serializable

/**
 * 用户与审核人
 *
 */
class Userauditor : Serializable {
    private var uaid: Int? = null//'编号',
    var userid: Int? = null//'用户ID',
    var username: String? = null//'用户名',
    var uauditorid: Int? = null//'审核人ID',
    var uauditor: String? = null//'审核人姓名'


    constructor()

    constructor(uaid: Int?, userid: Int?, username: String, uauditorid: Int?, uauditor: String) {

        this.uaid = uaid
        this.userid = userid
        this.username = username
        this.uauditorid = uauditorid
        this.uauditor = uauditor
    }

    fun getuaid(): Int? {
        return uaid
    }

    fun setuaid(uaid: Int?) {
        this.uaid = uaid
    }


}
