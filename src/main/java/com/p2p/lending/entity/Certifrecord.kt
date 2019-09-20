package com.p2p.lending.entity

import java.io.Serializable
import java.util.Date

/**
 * @Description:认证申请记录的bean
 * @author chenqingshan
 * @Date: 2017-2-23 Time: 20:54
 */
class Certifrecord : Serializable {
    var crid: Int? = null//编号
    var cruserid: Int? = null//用户编号
    var crusername: String? = null//用户姓名
    var craiid: Int? = null//认证项编号
    var crainame: String? = null//认证项名称
    var craitype: String? = null//认证项类型
    var crispass: String? = null//认证项状态
    var crdate: Date? = null//审核时间
    var crauditor: String? = null//跟踪审核
    var crintegral: Int? = null//信用积分
    var crviewpoint: String? = null//观点
    var crimg: String? = null//证件图片
    var crupldate: Date? = null//上传时间


    var checkpend: Int? = null//待审核

    constructor() {

    }


    constructor(crid: Int?, cruserid: Int?, crusername: String, craiid: Int?, crainame: String,
                craitype: String, crispass: String, crdate: Date, crauditor: String, crintegral: Int?, crviewpoint: String,
                crimg: String, crupldate: Date) {

        this.crid = crid
        this.cruserid = cruserid
        this.crusername = crusername
        this.craiid = craiid
        this.crainame = crainame
        this.craitype = craitype
        this.crispass = crispass
        this.crdate = crdate
        this.crauditor = crauditor
        this.crintegral = crintegral
        this.crviewpoint = crviewpoint
        this.crimg = crimg
        this.crupldate = crupldate
    }

}
