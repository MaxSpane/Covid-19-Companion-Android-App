package it.weMake.covid19Companion.utils

import android.app.AlarmManager
import android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.broadcastReceivers.DrinkWaterReminderBroadcast
import it.weMake.covid19Companion.broadcastReceivers.WashHandsReminderBroadcast


fun minutesToMilliSecs(value: Int): Long = value * 60 * 1000.toLong()

fun createRemindersNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name: CharSequence = "Reminders Channel"
        val description = "A channel for reminders to wash your hands and drink water"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel =
            NotificationChannel(REMINDERS_NOTIFICATION_CHANNEL_ID, name, importance)
        channel.description = description
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        channel.setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.cardi_b_corona_virus_alarm_tone), attributes)
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager: NotificationManager? = context.let {
            ContextCompat.getSystemService(
                it,
                NotificationManager::class.java
            )
        }

        notificationManager!!.createNotificationChannel(channel)

    }
}

fun showReminderNotification(context: Context, notificationChannelId: String, notificationId: Int, text: String){
    createRemindersNotificationChannel(context)
    val builder: NotificationCompat.Builder? = context.let {
        NotificationCompat.Builder(
            it,
            notificationChannelId
        )
            .setSmallIcon(R.drawable.ic_virus)
            .setContentTitle(context.getString(R.string.title_notification))
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.cardi_b_corona_virus_alarm_tone), AudioManager.STREAM_NOTIFICATION)

    }
    val notificationManager = NotificationManagerCompat.from(context)
    notificationManager.notify(notificationId, builder!!.build())
}

fun createCancelWashHandsAlarm(context: Context, time : Long){

    val intent = Intent(context.applicationContext, WashHandsReminderBroadcast::class.java)

    val pendingIntent =
        PendingIntent.getBroadcast(
            context.applicationContext,
            WASH_HANDS_PENDING_INTENT_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

    if (time == 0L){
        cancelAlarm(context, pendingIntent)
    }else{
        createAlarm(context, time, pendingIntent)
    }
}

fun createCancelDrinkWaterAlarm(context: Context, time : Long){

    val intent = Intent(context.applicationContext, DrinkWaterReminderBroadcast::class.java)

    val pendingIntent =
        PendingIntent.getBroadcast(
            context.applicationContext,
            DRINK_WATER_PENDING_INTENT_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

    if (time == 0L){
        cancelAlarm(context, pendingIntent)
    }else{
        createAlarm(context, time, pendingIntent)
    }
}

fun createAlarm (context: Context, time : Long, pendingIntent: PendingIntent) {

    val alarmManager =
        context.applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val currentTime = System.currentTimeMillis()

    val alarmTime = SystemClock.elapsedRealtime() + time

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        alarmManager.setExactAndAllowWhileIdle(ELAPSED_REALTIME_WAKEUP, alarmTime , pendingIntent)
    }else{
        alarmManager.setExact(ELAPSED_REALTIME_WAKEUP, alarmTime , pendingIntent)
    }

}

fun cancelAlarm (context: Context, pendingIntent: PendingIntent){
    val alarmManager =
        context.applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    alarmManager.cancel(pendingIntent)
    pendingIntent.cancel()

}
