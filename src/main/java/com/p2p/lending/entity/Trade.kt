package com.p2p.lending.entity

import java.io.Serializable
import java.util.Date

class Trade : Serializable {
    private var tID: Int? = null
    private var uID: Int? = null
    var uname: String? = null
    var zname: String? = null
    var jymoney: String? = null
    var what: String? = null
    var jytime: Date? = null
    var other: String? = null

    constructor() {}

    constructor(uID: Int?, uname: String, zname: String,
                jymoney: String, what: String, jytime: Date, other: String) {
        this.uID = uID
        this.uname = uname
        this.zname = zname
        this.jymoney = jymoney
        this.what = what
        this.jytime = jytime
        this.other = other
    }

    fun gettID(): Int? {
        return tID
    }

    fun settID(tID: Int?) {
        this.tID = tID
    }

    fun getuID(): Int? {
        return uID
    }

    fun setuID(uID: Int?) {
        this.uID = uID
    }


}
