package com.p2p.lending.entity

import java.util.Date

class Borrowcord : BaseDomain {

    var boid: Int? = null
    var bid: Int? = null
    var bdate: Date? = null
    var bstatue: Int? = null
    var bcs: Int? = null
    var bz1: String? = null
    var bz2: String? = null

    constructor() {
        // TODO Auto-generated constructor stub
    }

    constructor(boid: Int?, bid: Int?, bdate: Date, bstatue: Int?, bcs: Int?, bz1: String, bz2: String) {
        this.boid = boid
        this.bid = bid
        this.bdate = bdate
        this.bstatue = bstatue
        this.bcs = bcs
        this.bz1 = bz1
        this.bz2 = bz2
    }

}
