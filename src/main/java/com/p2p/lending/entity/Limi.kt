package com.p2p.lending.entity

class Limi : BaseDomain {

    var lid: Int? = null
    var eid: Int? = null
    var mid: String? = null

    constructor(lid: Int?, eid: Int?, mid: String) : super() {
        this.lid = lid
        this.eid = eid
        this.mid = mid
    }

    constructor() : super() {}


}
