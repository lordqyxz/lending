package com.p2p.lending.entity

import java.io.Serializable
import java.util.*

class Log : Serializable {

    var id: Int? = null
    var laccount: String? = null
    var logtype: String? = null
    var lremark: String? = null
    var lprocesstime: Date? = null

    var lprocestime: Date? = null

    constructor()
}
