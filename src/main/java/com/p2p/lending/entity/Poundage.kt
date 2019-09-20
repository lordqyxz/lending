package com.p2p.lending.entity

import java.io.Serializable
import java.util.Date

class Poundage : Serializable {
    private var pID: Int? = null
    private var uID: Int? = null
    var uname: String? = null
    var zname: String? = null
    var sxmoney: String? = null
    var what: String? = null
    var sxtime: Date? = null
    var localaccount: String? = null
    var bookaccount: String? = null
    var paytype: String? = null

    constructor() {}

    constructor(pID: Int?, uID: Int?, uname: String, zname: String,
                sxmoney: String, what: String, sxtime: Date) {
        this.pID = pID
        this.uID = uID
        this.uname = uname
        this.zname = zname
        this.sxmoney = sxmoney
        this.what = what
        this.sxtime = sxtime
    }

    fun getpID(): Int? {
        return pID
    }

    fun setpID(pID: Int?) {
        this.pID = pID
    }

    fun getuID(): Int? {
        return uID
    }

    fun setuID(uID: Int?) {
        this.uID = uID
    }

}
