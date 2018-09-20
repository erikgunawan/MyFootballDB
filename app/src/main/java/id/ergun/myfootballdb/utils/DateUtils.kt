package id.ergun.myfootballdb.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
val defaultDateFormat = SimpleDateFormat("yyyy-MM-dd")

@SuppressLint("SimpleDateFormat")
fun toLocalDate(dateString: String?): String {
    val id = Locale("id")
    val sdf = SimpleDateFormat("EEE, dd MMM yyyy", id)
    val date = defaultDateFormat.parse(dateString)
    return sdf.format(date)
}