package com.project.essentials

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.longToTime(): String {
    val date = Date(this)

    val dateString = SimpleDateFormat("HH:mm", Locale.getDefault()). format(date)

    return dateString
}