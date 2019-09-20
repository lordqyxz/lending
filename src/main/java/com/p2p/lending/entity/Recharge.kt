package com.p2p.lending.entity

import java.io.Serializable
import java.util.*

class Recharge : Serializable {
    private var rID: Int? = null
    private var uID: Int? = null
    var uname: String? = null
    var czlx: String? = null
    var lsh: String? = null
    var czmoney: String? = null
    var fl: String? = null
    var dzmoney: String? = null
    var cztime: Date? = null
    var statu: String? = null
    var zname: String? = null

    constructor() {}


    constructor(rID: Int?, uID: Int?, uname: String, czlx: String,
                lsh: String, czmoney: String, fl: String, dzmoney: String,
                cztime: Date, statu: String, zname: String) {
        this.rID = rID
        this.uID = uID
        this.uname = uname
        this.czlx = czlx
        this.lsh = lsh
        this.czmoney = czmoney
        this.fl = fl
        this.dzmoney = dzmoney
        this.cztime = cztime
        this.statu = statu
        this.zname = zname
    }


    fun getrID(): Int? {
        return rID
    }


    fun setrID(rID: Int?) {
        this.rID = rID
    }


    fun getuID(): Int? {
        return uID
    }


    fun setuID(uID: Int?) {
        this.uID = uID
    }

}
