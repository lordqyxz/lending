package com.p2p.lending.entity

import java.io.Serializable
import java.util.Date

class Clapplyfor : Serializable {
    //	 `clpid`   '申请编号',
    //	  `clpubcid`   '用户账号',
    //	  `clpuname`   '用户姓名',
    //	  `clpporiginal`  '原信用额',
    //	  `clpidpaper`  '身份证明照片',
    //	  `clphpoc`   '房产证明照片',
    //	  `clpf`  '申请信用额',
    //	  `clpdate`  '申请时间',
    //	  `clpauditor`  '跟踪审核',
    //	  `clpstate`  '状态',
    var clpid: Int? = null
    var clpubcid: String? = null
    var clpuname: String? = null
    var clpporiginal: Double? = null
    var clpidpaper: String? = null
    var clphpoc: String? = null
    var clpf: Double? = null
    var clpdate: Date? = null
    var clpauditor: String? = null
    var clpstate: String? = null


    constructor() {

    }

    constructor(clpid: Int?, clpubcid: String, clpuname: String, clpporiginal: Double?, clpidpaper: String,
                clphpoc: String, clpf: Double?, clpdate: Date, clpauditor: String, clpstate: String) {

        this.clpid = clpid
        this.clpubcid = clpubcid
        this.clpuname = clpuname
        this.clpporiginal = clpporiginal
        this.clpidpaper = clpidpaper
        this.clphpoc = clphpoc
        this.clpf = clpf
        this.clpdate = clpdate
        this.clpauditor = clpauditor
        this.clpstate = clpstate
    }


}
