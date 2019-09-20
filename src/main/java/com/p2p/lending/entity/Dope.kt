package com.p2p.lending.entity

import java.io.Serializable
import java.util.*

class Dope : Serializable {
    var did: Int? = null
    var dprimkey: Int? = null
    var dtitle: String? = null
    var details: String? = null
    var dtime: Date? = null

    constructor()


    constructor(did: Int?, dprimkey: Int?, dtitle: String, details: String, dtime: Date) : super() {
        this.did = did
        this.dprimkey = dprimkey
        this.dtitle = dtitle
        this.details = details
        this.dtime = dtime
    }


}
