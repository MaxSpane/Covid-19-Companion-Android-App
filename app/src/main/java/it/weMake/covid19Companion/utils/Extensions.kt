package it.weMake.covid19Companion.utils

import android.text.format.DateUtils
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Int.numberWithCommas(): String {
    val formatter = DecimalFormat("#,###,###")
    return formatter.format(this.toDouble())
}

fun String.getTimeFromToday(): String {

    val calendar = Calendar.getInstance(
        TimeZone.getDefault(),
        Locale.getDefault()
    )
    val date: Date = this.getDateFromISOString()
    calendar.time = date

    return DateUtils.getRelativeTimeSpanString(calendar.timeInMillis, Calendar.getInstance().timeInMillis, android.text.format.DateUtils.MINUTE_IN_MILLIS, android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE).toString()
}


fun String.getDateFromISOString(): Date {
    val dateformat =
        SimpleDateFormat(ISO8601_DATE_FORMAT, Locale.getDefault())
    var date: Date? = null
    try {
        date = dateformat.parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return date!!
}


fun Long.getTimeFromToday(): String {

    val calendar = Calendar.getInstance(
        TimeZone.getDefault(),
        Locale.getDefault()
    )
    val date: Date = Date(this)
    calendar.time = date

    return DateUtils.getRelativeTimeSpanString(calendar.timeInMillis, Calendar.getInstance().timeInMillis, DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE).toString()
}
