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
    return try {
        val id = Locale("id")
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy", id)
        val date = defaultDateFormat.parse(dateString)
        sdf.format(date)
    } catch (exception: Exception) {
        dateString.toString()
    }
}

fun toLocalTime(timeString: String?): String {
    return try {
        val id = Locale("id")
        val sdf = SimpleDateFormat("HH:mm", id)
        val time = defaultTimeFormat.parse(timeString)
      return  sdf.format(time)
    } catch (exception: Exception) {
        timeString.toString()
    }
}

fun getTimeInMillis(datetimeString: String): Long {
    val calendar = Calendar.getInstance()
    val id = Locale("id")
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZZZ", id)
    val date = sdf.parse(datetimeString)
    calendar.time = date

    return calendar.timeInMillis
}