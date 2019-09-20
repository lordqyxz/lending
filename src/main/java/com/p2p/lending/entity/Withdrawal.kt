package com.p2p.lending.entity

import java.io.Serializable
import java.util.Date

/**
 *
 * @author Hjy 提现管理表
 */

class Withdrawal : Serializable {

    private var wID: Int? = null
    private var uID: Int? = null
    var uname: String? = null
    var zname: String? = null
    var txnum: String? = null
    var txbank: String? = null
    var txmoney: String? = null
    var dzmoney: String? = null
    var sxf: String? = null
    var txtime: Date? = null
    var zztime: Date? = null
    var statu: String? = null
    var shwho: String? = null
    var shtime: Date? = null
    var nothing: String? = null

    constructor() {}

    constructor(wID: Int?, uID: Int?, uname: String, zname: String, txnum: String, txbank: String, txmoney: String,
                dzmoney: String, sxf: String, txtime: Date, zztime: Date, statu: String, shwho: String, shtime: Date,
                nothing: String) {
        this.wID = wID
        this.uID = uID
        this.uname = uname
        this.zname = zname
        this.txnum = txnum
        this.txbank = txbank
        this.txmoney = txmoney
        this.dzmoney = dzmoney
        this.sxf = sxf
        this.txtime = txtime
        this.zztime = zztime
        this.statu = statu
        this.shwho = shwho
        this.shtime = shtime
        this.nothing = nothing
    }

    override fun toString(): String {
        return ("Withdrawal [wID=" + wID + ", uID=" + uID + ", uname=" + uname + ", zname=" + zname + ", txnum=" + txnum
                + ", txbank=" + txbank + ", txmoney=" + txmoney + ", dzmoney=" + dzmoney + ", sxf=" + sxf + ", txtime="
                + txtime + ", zztime=" + zztime + ", statu=" + statu + ", shwho=" + shwho + ", shtime=" + shtime
                + ", nothing=" + nothing + "]")
    }

    fun getwID(): Int? {
        return wID
    }

    fun setwID(wID: Int?) {
        this.wID = wID
    }

    fun getuID(): Int? {
        return uID
    }

    fun setuID(uID: Int?) {
        this.uID = uID
    }


}
