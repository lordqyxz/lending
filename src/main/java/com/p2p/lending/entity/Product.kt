package com.p2p.lending.entity

import org.springframework.format.annotation.DateTimeFormat
import java.util.*

class Product : Serializable() {
    var pmoney: Int? = null
    var startPage: Int? = null
    var pageSize: Int? = null
    var id: Int? = null
    var pincome: String? = null
        set(pincome) {
            field = pincome?.trim { it <= ' ' }
        }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var ptime: Date? = null
    var ptype: Int? = null
    var pway: String? = null
        set(pway) {
            field = pway?.trim { it <= ' ' }
        }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var pcount: Date? = null
    var progress: String? = null
        set(progress) {
            field = progress?.trim { it <= ' ' }
        }
    var psaveway: String? = null
        set(psaveway) {
            field = psaveway?.trim { it <= ' ' }
        }
    var prateben: String? = null
        set(prateben) {
            field = prateben?.trim { it <= ' ' }
        }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var ppublishtime: Date? = null
    var pname: String? = null
        set(pname) {
            field = pname?.trim { it <= ' ' }
        }
    var ptotalmoney: Int? = null
    var prange: String? = null
        set(prange) {
            field = prange?.trim { it <= ' ' }
        }
    var puse: String? = null
        set(puse) {
            field = puse?.trim { it <= ' ' }
        }
    var pstate: String? = null
        set(pstate) {
            field = pstate?.trim { it <= ' ' }
        }
    var picture: String? = null
        set(picture) {
            field = picture?.trim { it <= ' ' }
        }
    var pproduce: Int? = null
    var pdesc: String? = null
        set(pdesc) {
            field = pdesc?.trim { it <= ' ' }
        }
    var psafe: String? = null
        set(psafe) {
            field = psafe?.trim { it <= ' ' }
        }

    init {
        this.pmoney = 0
        this.pproduce = 0
        this.ptotalmoney = 0
        this.pcount = Date()
        this.ppublishtime = Date()
        this.ptime = Date()
    }
}