package com.p2p.lending.util

import org.springframework.core.convert.converter.Converter

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class DateConverter : Converter<String, Date> {
    override fun convert(source: String): Date? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.isLenient = false
        try {
            return dateFormat.parse(source)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}
