package com.diary

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun convertDateToDoematted(created: Long): String {
    var date = Date(created)
    var formater = SimpleDateFormat("dd.MM")
    return  formater.format(date)
}
