package com.p2p.lending.entity

import java.io.Serializable
import java.util.Date

/**
 *
 * @author Hjy 银行卡管理表
 */
class Bankcard : Serializable {
    private var bID: Int? = null
    private var uID: Int? = null
    var uname: String? = null
    var zname: String? = null
    var sfz: String? = null
    var khh: String? = null
    var cardid: String? = null
    var tjtime: Date? = null
    var statu: String? = null

    constructor() {}

    constructor(bID: Int?, uID: Int?, uname: String, zname: String, sfz: String, khh: String, cardid: String,
                tjtime: Date, statu: String) {
        this.bID = bID
        this.uID = uID
        this.uname = uname
        this.zname = zname
        this.sfz = sfz
        this.khh = khh
        this.cardid = cardid
        this.tjtime = tjtime
        this.statu = statu
    }

    override fun toString(): String {
        return ("Bankcard [bID=" + bID + ", uID=" + uID + ", uname=" + uname + ", zname=" + zname + ", sfz=" + sfz
                + ", khh=" + khh + ", cardid=" + cardid + ", tjtime=" + tjtime + ", statu=" + statu + "]")
    }

    fun getbID(): Int? {
        return bID
    }

    fun setbID(bID: Int?) {
        this.bID = bID
    }

    fun getuID(): Int? {
        return uID
    }

    fun setuID(uID: Int?) {
        this.uID = uID
    }

}
