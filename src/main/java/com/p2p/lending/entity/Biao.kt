package com.p2p.lending.entity

class Biao : BaseDomain() {
    var startPage: Int? = null


    var pageSize: Int? = null


    var id: Int? = null

    var bname: String? = null
        set(bname) {
            field = bname?.trim { it <= ' ' }
        }
}