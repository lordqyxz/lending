package com.p2p.lending.util

import java.text.SimpleDateFormat
import java.util.*

class TimeCompare {

    fun Compare(DATE1: String, DATE2: String): Int {

        val df = SimpleDateFormat("yyyy-MM-dd hh:mm")
        try {
            val dt1 = df.parse(DATE1)
            val dt2 = df.parse(DATE2)
            if (dt1.time < dt2.time) {
                println("dt1   =  " + dt1.time)
                println("dt2   =  " + dt2.time)
                println("dt1 在dt2前")
                return 1
            } else if (dt1.time > dt2.time) {
                println("dt1在dt2后")
                return -1
            } else {
                return 0
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return 0
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val df = SimpleDateFormat("yyyy-MM-dd hh:mm")
            try {
                val dt1 = df.parse(Date().toString())
                val dt2 = df.parse("2017-01-12 00:00:00")
                println("dt1   =  " + dt1.time)
                println("dt2   =  " + dt2.time)
                if (dt1.time < dt2.time) {
                    println("现在时间比项目到期时间早")
                } else if (dt1.time > dt2.time) {
                    println("现在时间比项目到期时间晚")
                } else {

                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

        }
    }
}
