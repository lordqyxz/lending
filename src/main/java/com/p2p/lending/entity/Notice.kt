package com.p2p.lending.entity

import java.util.Date

class Notice : Serializable{
    // noticeid int(11) NOT NULL AUTO_INCREMENT COMMENT '公告表id',
    // noticetitle varchar(255) DEFAULT NULL COMMENT '公告表标题',
    // noticetype varchar(255) DEFAULT NULL COMMENT '公告类型',
    // noticepicture varchar(255) DEFAULT NULL COMMENT '公告图片',
    // noticecontent varchar(255) DEFAULT NULL COMMENT '公告内容',
    // noticelasttime datetime DEFAULT NULL COMMENT '公告最后修改时间,发布时间',
    // noticelastmodifier int(11) DEFAULT NULL COMMENT '公告最后发布人,操作人.',

    var noticeid: Int = 0
    var noticetitle: String? = null
    var noticetype: String? = null
    var noticepicture: String? = null
    var noticecontent: String? = null
    var noticelasttime: Date? = null
    var noticelastmodifier: Int = 0

    constructor() {
        // TODO Auto-generated constructor stub
    }

    constructor(noticeid: Int, noticetitle: String, noticetype: String, noticepicture: String, noticecontent: String,
                noticelasttime: Date, noticelastmodifier: Int) : super() {
        this.noticeid = noticeid
        this.noticetitle = noticetitle
        this.noticetype = noticetype
        this.noticepicture = noticepicture
        this.noticecontent = noticecontent
        this.noticelasttime = noticelasttime
        this.noticelastmodifier = noticelastmodifier
    }

}
