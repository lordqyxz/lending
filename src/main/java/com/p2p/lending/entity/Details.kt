package com.p2p.lending.entity

class Details {
    var did: Int? = null

    var dname: String? = null
        set(dname) {
            field = dname?.trim { it <= ' ' }
        }

    var dcontent: String? = null
        set(dcontent) {
            field = dcontent?.trim { it <= ' ' }
        }

    var dtype: Int? = null

    var pid: Int? = null
}