package com.p2p.lending.entity

class Borrowmoney : Serializable() {
    var id: Int? = null
    var startPage: Int? = null
    var pageSize: Int? = null
    var bleixing: Int? = null

    var bserial: String? = null

    var busername: String? = null

    var brelname: String? = null

    var bpass: String? = null

    var btype: String? = null

    var btitle: String? = null

    var bmoney: String? = null

    var brate: String? = null

    var btimelimit: String? = null

    var blimit: String? = null

    var bstate: String? = null

    var brecommend: String? = null

    var beizhu1: String? = null

    var beizhu2: String? = null


    var biao: Biao? = null
    override fun toString(): String {
        return ("Borrowmoney [id=" + id + ", startPage=" + startPage + ", pageSize=" + pageSize + ", bleixing="
                + bleixing + ", bserial=" + bserial + ", busername=" + busername + ", brelname=" + brelname + ", bpass="
                + bpass + ", btype=" + btype + ", btitle=" + btitle + ", bmoney=" + bmoney + ", brate=" + brate
                + ", btimelimit=" + btimelimit + ", blimit=" + blimit + ", bstate=" + bstate + ", brecommend="
                + brecommend + ", beizhu1=" + beizhu1 + ", beizhu2=" + beizhu2 + "]")
    }

}// TODO Auto-generated constructor stub