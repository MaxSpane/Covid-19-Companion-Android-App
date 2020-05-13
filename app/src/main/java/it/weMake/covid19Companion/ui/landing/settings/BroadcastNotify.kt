package it.weMake.covid19Companion.ui.landing.settings

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import it.weMake.covid19Companion.utils.EXTRA_ALARM_INTERVAL
import it.weMake.covid19Companion.utils.createAlarm
import it.weMake.covid19Companion.utils.createNotificationChannel
import javax.inject.Inject


class BroadcastNotify : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        createNotificationChannel(context)
        val builder: NotificationCompat.Builder? = context?.let {
            NotificationCompat.Builder(
                it,
                "lemubitNotify"
            )
                .setSmallIcon(R.drawable.ic_btn_speak_now)
                .setContentTitle("Lemubit Academy Alarm notification")
                .setContentText("Hey students, this is an awesome alarm notification...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        val notificationManager = context?.let { NotificationManagerCompat.from(it) }
        if (notificationManager != null) {
            notificationManager.notify(101, builder!!.build())
        }
        val time = intent!!.getLongExtra(EXTRA_ALARM_INTERVAL, 0)
        createAlarm(context, time)
    }
}