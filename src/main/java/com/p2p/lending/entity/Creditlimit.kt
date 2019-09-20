package com.p2p.lending.entity

import java.io.Serializable

/**
 * @Description:信用额度的bean
 * @author chenqingshan
 * @Date: 2017-2-23 Time: 20:40
 */
class Creditlimit : Serializable {

    var clid: Int? = null
    var cluserid: Int? = null
    var clusername: String? = null
    var crbankcard: String? = null
    var cllimit: Double? = null

    val creditlimitInfo: String
        get() = ("Info clid==" + this.clid + "  cluserid==" + this.cluserid + "  clusername=="
                + this.clusername + "  crbankcard==" + this.crbankcard + "  cllimit==" + this.cllimit)

    constructor()

    constructor(clid: Int?, cluserid: Int?, clusername: String, crbankcard: String, cllimit: Double?) {

        this.clid = clid
        this.cluserid = cluserid
        this.clusername = clusername
        this.crbankcard = crbankcard
        this.cllimit = cllimit
    }

}
