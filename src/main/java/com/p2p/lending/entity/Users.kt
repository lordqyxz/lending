package com.p2p.lending.entity

import java.util.*

class Users : Serializable {
    var uid: Int? = null
    var unickname: String? = null
    var ucardid: String? = null
    var uhead: String? = null
    var uname: String? = null
    var umailbox: String? = null
    var uphonenumber: String? = null
    var uregisterdate: Date? = null
    var uflip: String? = null
    var ufldate: Date? = null
    var ureferrer: String? = null
    var ureferrername: String? = null
    var userpaytoid: String? = null
    var ucertnumber: String? = null
    var upassword: String? = null
    var upwd_zd: String? = null

    constructor() {

    }

    constructor(uid: Int?, unickname: String, ucardid: String, uhead: String, uname: String, umailbox: String,
                uphonenumber: String, uregisterdate: Date, uflip: String, ufldate: Date, ureferrer: String, ureferrername: String,
                userpaytoid: String, ucertnumber: String, upassword: String) {

        this.uid = uid
        this.unickname = unickname
        this.ucardid = ucardid
        this.uhead = uhead
        this.uname = uname
        this.umailbox = umailbox
        this.uphonenumber = uphonenumber
        this.uregisterdate = uregisterdate
        this.uflip = uflip
        this.ufldate = ufldate
        this.ureferrer = ureferrer
        this.ureferrername = ureferrername
        this.userpaytoid = userpaytoid
        this.ucertnumber = ucertnumber
        this.upassword = upassword
    }

}
