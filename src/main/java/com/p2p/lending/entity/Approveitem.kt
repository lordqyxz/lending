package com.p2p.lending.entity

import java.io.Serializable

/**
 * @Name: Approveitem
 * @Description:认证项的JavaBeans
 */
class Approveitem : Serializable {

    var aiid: Int? = null// 认证项编号
    var ainame: String? = null// 认证项名称
    var aitype: String? = null// 认证项类型 1.基本认证项；2.可选认证项
    var aistate: String? = null// 认证项状态 0.已弃用；1.未弃用

    /**
     *
     * @return 该对象的所有值
     */
    val approveitemInfo: String
        get() = ("Info aiid==" + this.aiid + "  ainame==" + this.ainame + "  aitype==" + this.aitype
                + "  aistate==" + this.aistate)

    constructor() {

    }

    constructor(aiid: Int?, ainame: String, aitype: String, aistate: String) {

        this.aiid = aiid
        this.ainame = ainame
        this.aitype = aitype
        this.aistate = aistate
    }

}
