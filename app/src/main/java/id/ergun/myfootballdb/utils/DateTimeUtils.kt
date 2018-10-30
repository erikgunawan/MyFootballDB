package id.ergun.myfootballdb.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
val defaultDateFormat = SimpleDateFormat("yyyy-MM-dd")

@SuppressLint("SimpleDateFormat")
val defaultTimeFormat = SimpleDateFormat("HH:mm:ssZZZZZ")

@SuppressLint("SimpleDateFormat")
fun toLocalDate(dateString: String?): String {
    val id = Locale("id")
    val sdf = SimpleDateFormat("EEE, dd MMM yyyy", id)
    val date = defaultDateFormat.parse(dateString)
    return sdf.format(date)
}

fun toSimpleTime(timeString: String?): String {
    val sdf = SimpleDateFormat("HH:mm")
    val time = defaultTimeFormat.parse(timeString)
    return sdf.format(time)
}

fun toLocalTime(timeString: String?): String {
    val id = Locale("id")
    val sdf = SimpleDateFormat("HH:mm", id)
    val time = defaultTimeFormat.parse(timeString)
    return sdf.format(time)
}