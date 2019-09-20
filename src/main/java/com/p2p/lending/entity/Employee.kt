package com.p2p.lending.entity

import java.util.Date

class Employee : BaseDomain {

    var eid: Int? = null
    var ename: String? = null
    var esex: String? = null
    var ebirth: Date? = null
    var eidcard: String? = null
    var ephone: String? = null
    var email: String? = null
    var edeptno: Int? = null
    var epostno: String? = null
    var etime: Date? = null
    var epassword: String? = null
    var estatus: Int? = null

    constructor() : super() {}

    constructor(eid: Int?, ename: String, esex: String, ebirth: Date, eidcard: String, ephone: String, email: String,
                edeptno: Int?, epostno: String, etime: Date, epassword: String, estatus: Int?) {

        this.eid = eid
        this.ename = ename
        this.esex = esex
        this.ebirth = ebirth
        this.eidcard = eidcard
        this.ephone = ephone
        this.email = email
        this.edeptno = edeptno
        this.epostno = epostno
        this.etime = etime
        this.epassword = epassword
        this.estatus = estatus
    }

}
