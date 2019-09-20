package com.p2p.lending.entity

import java.io.Serializable

class Certification : Serializable {
    var id: Int? = null

    var cserial: String? = null
        set(cserial) {
            field = cserial?.trim { it <= ' ' }
        }

    var cusername: String? = null
        set(cusername) {
            field = cusername?.trim { it <= ' ' }
        }

    var crealname: String? = null
        set(crealname) {
            field = crealname?.trim { it <= ' ' }
        }

    var cbalance: String? = null
        set(cbalance) {
            field = cbalance?.trim { it <= ' ' }
        }

    var cfreeze: String? = null
        set(cfreeze) {
            field = cfreeze?.trim { it <= ' ' }
        }

    var cdue: String? = null
        set(cdue) {
            field = cdue?.trim { it <= ' ' }
        }

    var cpaid: String? = null
        set(cpaid) {
            field = cpaid?.trim { it <= ' ' }
        }

    var ctotalmoney: String? = null
        set(ctotalmoney) {
            field = ctotalmoney?.trim { it <= ' ' }
        }

    constructor()
}