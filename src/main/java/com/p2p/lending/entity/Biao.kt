package com.p2p.lending.entity

import java.io.Serializable

class Biao : Serializable {
    var startPage: Int? = null


    var pageSize: Int? = null


    var id: Int? = null

    var bname: String? = null
        set(bname) {
            field = bname?.trim { it <= ' ' }
        }
}