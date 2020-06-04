package it.weMake.covid19Companion.utils

import android.app.*
import android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.broadcastReceivers.BootReceiver
import it.weMake.covid19Companion.broadcastReceivers.DrinkWaterReminderBroadcast
import it.weMake.covid19Companion.broadcastReceivers.WashHandsReminderBroadcast
import it.weMake.covid19Companion.ui.about.AboutActivity
import it.weMake.covid19Companion.ui.landing.MainActivity
import it.weMake.covid19Companion.ui.splashscreen.SplashScreenActivity


fun minutesToMilliSecs(value: Int): Long = value * 60 * 1000.toLong()

fun createRemindersNotificationChannel(context: Context, useCustomNotificationTone: Boolean) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name: CharSequence = "Reminders Channel with Default Tone"
        val importance = NotificationManager.IMPORTANCE_HIGH

        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        val soundUri =
            if (useCustomNotificationTone){
                Uri.parse("android.resource://" + context.packageName + "/" + R.raw.cardi_b_corona_virus_alarm_tone)
            }else{
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            }

        val channel =
            if (useCustomNotificationTone){
                NotificationChannel(
                    REMINDERS_NOTIFICATION_CUSTOM_TONE_CHANNEL_ID,
                    context.getString(R.string.channel_name_reminders_custom_tone),
                    importance)
                    .apply {
                        this.description = context.getString(R.string.channel_desc_reminders_custom_tone)
                    }
            }else{
                NotificationChannel(
                    REMINDERS_NOTIFICATION_DEFAULT_TONE_CHANNEL_ID,
                    context.getString(R.string.channel_name_reminders_default_tone),
                    importance)
                    .apply {
                        this.description = context.getString(R.string.channel_desc_reminders_default_tone)
                    }
            }

        channel.setSound(soundUri, attributes)
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager: NotificationManager? = ContextCompat.getSystemService(context, NotificationManager::class.java)

        notificationManager?.createNotificationChannel(channel)

    }
}

fun createDefaultNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name: CharSequence = "Default Notification Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(
            DEFAULT_NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.channel_name_default),
            importance)
            .apply {
                this.description = context.getString(R.string.channel_desc_default)
            }

        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager: NotificationManager? = ContextCompat.getSystemService(context, NotificationManager::class.java)

        notificationManager?.createNotificationChannel(channel)

    }
}

fun showReminderNotification(
    context: Context,
    notificationId: Int,
    text: String,
    useCustomNotificationTone: Boolean
) {

    createRemindersNotificationChannel(context, useCustomNotificationTone)

    val channelId =
        if (useCustomNotificationTone){
            REMINDERS_NOTIFICATION_CUSTOM_TONE_CHANNEL_ID
        }else{
            REMINDERS_NOTIFICATION_DEFAULT_TONE_CHANNEL_ID
        }

    val intent = Intent(context, SplashScreenActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_virus)
        .setContentTitle(context.getString(R.string.title_notification))
        .setContentText(text)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_REMINDER)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
    val soundUri =
        if (useCustomNotificationTone){
            Uri.parse("android.resource://" + context.packageName + "/" + R.raw.cardi_b_corona_virus_alarm_tone)
        }else{
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
    builder.setSound(soundUri)

    val notificationManager = NotificationManagerCompat.from(context)
    notificationManager.notify(notificationId, builder.build())
}

fun showDefaultNotification(
    context: Context,
    notificationId: Int,
    title: String,
    text: String,
    intent: Intent? = null
) {

    createDefaultNotificationChannel(context)

    val channelId = DEFAULT_NOTIFICATION_CHANNEL_ID

    val pendingIntent: PendingIntent =
        if (intent == null){
            val intent = Intent(context, SplashScreenActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            PendingIntent.getActivity(context, 0, intent, 0)!!
        }else{
            TaskStackBuilder.create(context).addNextIntent(Intent(context, MainActivity::class.java)).addNextIntent(intent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)!!
        }

    val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_virus)
        .setContentTitle(title)
        .setContentText(text)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setStyle(NotificationCompat.BigTextStyle().bigText(text))
    val notificationManager = NotificationManagerCompat.from(context)
    notificationManager.notify(notificationId, builder.build())
}

fun createCancelWashHandsAlarm(context: Context, time : Long){

    val intent = Intent(context.applicationContext, WashHandsReminderBroadcast::class.java)

    val pendingIntent =
        PendingIntent.getBroadcast(
            context.applicationContext,
            WASH_HANDS_PENDING_INTENT_REQUEST_CODE,
            intent,
            0)

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

fun enableBootReceiver(context: Context){
    val receiver = ComponentName(context, BootReceiver::class.java)

    context.packageManager.setComponentEnabledSetting(
        receiver,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP
    )
}

fun disableBootReceiver(context: Context){
    val receiver = ComponentName(context, BootReceiver::class.java)

    context.packageManager.setComponentEnabledSetting(
        receiver,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP
    )
}

fun isBootReceiverEnabled(context: Context): Boolean{
    val receiver = ComponentName(context, BootReceiver::class.java)

    return context.packageManager.getComponentEnabledSetting(receiver) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
}