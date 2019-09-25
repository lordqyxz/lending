package com.p2p.lending

import java.util.*

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        var date = Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30)
        val calendar = Calendar.getInstance()// 时间转换
        for (i in 0..11) {
            calendar.time = date
            calendar.add(Calendar.SECOND, 60 * 60 * 24 * 30)
            date = calendar.time
            println(date.toString())

        }

    }

}
