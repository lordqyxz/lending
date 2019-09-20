package com.p2p.lending.entity

import java.io.Serializable
import java.util.*

class Dept : Serializable {

    var did: Int? = null

    var dname: String? = null
    var dtime: Date? = null
    var dstatus: Int? = null
    var describes: String? = null

    constructor(did: Int?, dname: String, dtime: Date, dstatus: Int?, describes: String) {

        this.did = did
        this.dname = dname
        this.dtime = dtime
        this.dstatus = dstatus
        this.describes = describes
    }

    constructor()

}
