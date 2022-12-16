package com.aptiv.fika.app

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {
    @JvmStatic
    fun toSimpleString(date: Date): String {
        val format = SimpleDateFormat("dd MMM yyy")
        return format.format(date)
    }
}