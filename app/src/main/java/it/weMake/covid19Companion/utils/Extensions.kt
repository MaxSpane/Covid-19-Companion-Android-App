package it.weMake.covid19Companion.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.text.format.DateUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
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

fun Fragment.isStoragePermissionGranted(): Boolean{

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            return true
        }else{
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST)
            return false
        }
    }else{
        return true
    }

}

fun AppCompatActivity.isStoragePermissionGranted(): Boolean{

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            return true
        }else{
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST)
            return false
        }
    }else{
        return true
    }

}
