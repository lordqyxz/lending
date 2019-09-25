package com.p2p.lending.util.un

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ZqUtil {

    fun strchangedate(date: String): Date? {
        var date = date
        try {
            if (date.length < 12) {
                date = "$date 00:00:00"
            }
            val sim = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sim.parse(date)
        } catch (e: ParseException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return null
    }

}
