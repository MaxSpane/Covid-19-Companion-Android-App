package it.weMake.covid19Companion.utils

import android.app.AlarmManager
import android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.content.ContextCompat
import it.weMake.covid19Companion.ui.landing.settings.BroadcastNotify

fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name: CharSequence = "lemubit Channel"
        val description = "You gotta listen"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel =
            NotificationChannel("lemubitNotify", name, importance)
        channel.description = description
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager: NotificationManager? = context?.let {
            ContextCompat.getSystemService(
                it,
                NotificationManager::class.java
            )
        }

        notificationManager!!.createNotificationChannel(channel)

    }
}

fun createAlarm (context: Context, time : Long) {

    val intent = Intent(context.applicationContext, BroadcastNotify::class.java)
    intent.putExtra(EXTRA_ALARM_INTERVAL, 60000)
    val pendingIntent =
        PendingIntent.getBroadcast(context.applicationContext, 0, intent, 0)
    val alarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val currentTime = System.currentTimeMillis()

//    alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo( currentTime + time,pendingIntent)
//        , pendingIntent
//    )
    val alarmTime = SystemClock.elapsedRealtime() + time

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        alarmManager.setExactAndAllowWhileIdle(ELAPSED_REALTIME_WAKEUP, alarmTime , pendingIntent)
    }else{
        alarmManager.setExact(ELAPSED_REALTIME_WAKEUP, alarmTime , pendingIntent)
    }

}

fun cancelAlarm (context: Context){
    val intent = Intent(context.applicationContext, BroadcastNotify::class.java)
    intent.putExtra(EXTRA_ALARM_INTERVAL, 60000)
    val pendingIntent =
        PendingIntent.getBroadcast(context.applicationContext, 0, intent, 0)
    val alarmManager =
        context.applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent)

}

fun createAlarmPendingIntent(){

}