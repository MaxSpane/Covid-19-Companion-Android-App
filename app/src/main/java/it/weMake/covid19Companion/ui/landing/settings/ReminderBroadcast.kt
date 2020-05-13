package it.weMake.covid19Companion.ui.landing.settings

import android.R
import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class ReminderBroadcast: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Notification.Builder(p0)
            .setContentTitle("New Message")
            .setContentText("You've received new messages.")
            .setSmallIcon(R.drawable.ic_btn_speak_now)
//            .setChannelId("")
            .build()

    }
}