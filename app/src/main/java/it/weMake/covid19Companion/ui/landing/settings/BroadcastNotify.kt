package it.weMake.covid19Companion.ui.landing.settings

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class BroadcastNotify : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
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
    }
}