package it.weMake.covid19Companion.ui.landing.settings

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment()  {

    private lateinit var settingsViewModel: SettingsViewModel
    lateinit var fragmentBinding: FragmentSettingsBinding
    lateinit var reminderLocationAdapter: ReminderLocationAdapter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // We set the listener on the child fragmentManager
//        childFragmentManager.setResultListener("requestKey") { key, bundle ->
//            val result = bundle.getString("bundleKey")
//            // Do something with the result..
//        }
//    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        settingsViewModel =
                ViewModelProvider(this).get(SettingsViewModel::class.java)
        fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        reminderLocationAdapter = ReminderLocationAdapter()
        fragmentBinding.remHandLocRV.adapter = reminderLocationAdapter
         createNotificationChannel();


        fragmentBinding.remWashHandsS.setOnClickListener {
            //checking notification code #delete later probably ?

            val intent = Intent(requireContext(), BroadcastNotify::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(requireContext(), 0, intent, 0)
            val alarmManager =
                requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
            val currentTime = System.currentTimeMillis()
            //1000 milli= 1 sec
            // val twoMinutes = 30 * 1000.toLong()
            val twoMinutes = 120  * 1000.toLong()



            if (fragmentBinding.remWashHandsS.isChecked) {
                alarmManager.setRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + twoMinutes,twoMinutes,
                    pendingIntent
                )
                openWashHandsDialog()
            }else{
                alarmManager.cancel(pendingIntent)
                setIntervalWashHand(0)
            }
        }
        fragmentBinding.remDrinkWaterS.setOnClickListener {
            if (fragmentBinding.remDrinkWaterS.isChecked) {
                openDrinkWaterDialog()
            }else{
                setIntervalDrinkWater(0)
            }
        }

        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return fragmentBinding.root
    }

    private fun setIntervalWashHand(intervalInMinutes: Int){
         if (intervalInMinutes == 0)   {
            fragmentBinding.remHandTime.text = getString(R.string.default_text_wash_hands_reminder)
             fragmentBinding.remWashHandsS.isChecked = false
        }else{
            fragmentBinding.remHandTime.text = getString(R.string.placeholder_wash_hands_reminder, convertIntervalToText(intervalInMinutes))
        }
    }

    private fun setIntervalDrinkWater(intervalInMinutes: Int){
        if (intervalInMinutes == 0)   {
            fragmentBinding.remWater.text = getString(R.string.default_text_drink_water_reminder)
            fragmentBinding.remWashHandsS.isChecked = false
        }else{
            fragmentBinding.remWater.text = getString(R.string.placeholder_drink_water_reminder, convertIntervalToText(intervalInMinutes))
        }
    }

    fun convertIntervalToText(intervalInMinutes: Int): String{
        val hour = intervalInMinutes / 60
        val minute = intervalInMinutes % 60

        var intervalText = ""

        if(hour > 0){
            intervalText += "$hour hour"
            if (hour > 1)
                intervalText += "s"

            intervalText += " "
        }

        if(minute > 0){

            if (hour > 0)
                intervalText += "and "

            intervalText += "$minute minute"
            if (minute > 1)
                intervalText += "s"
        }

        return intervalText
    }

    fun openWashHandsDialog() {
        val reminderDialog = ReminderDialog{intervalInMinutes->
            setIntervalWashHand(intervalInMinutes)
        }
        reminderDialog.show(childFragmentManager, "example dialog")
    }
    fun openDrinkWaterDialog() {
        val reminderDialog = ReminderDialog{intervalInMinutes->
            setIntervalDrinkWater(intervalInMinutes)
        }
        reminderDialog.show(childFragmentManager, "example dialog")
    }

    private fun createNotificationChannel() {
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
                getSystemService(
                    it,
                    NotificationManager::class.java
                )
            }

            notificationManager!!.createNotificationChannel(channel)

        }
    }


}

//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        createNotificationChannel()
//        findViewById<View>(R.id.button).setOnClickListener {
//            Toast.makeText(this@MainActivity, "Alarm Set!...", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this@MainActivity, BroadcastNotify::class.java)
//            val pendingIntent =
//                PendingIntent.getBroadcast(this@MainActivity, 0, intent, 0)
//            val alarmManager =
//                getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            val currentTime = System.currentTimeMillis()
//            //1000 milli= 1 sec
//            val tenSeconds = 10 * 1000.toLong()
//            alarmManager[AlarmManager.RTC_WAKEUP, currentTime + tenSeconds] = pendingIntent
//        }
//    }
//
//    private fun createNotificationChannel() { // Create the NotificationChannel, but only on API 26+ because
//// the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name: CharSequence = "lemubit Channel"
//            val description = "You gotta listen"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel =
//                NotificationChannel("lemubitNotify", name, importance)
//            channel.description = description
//            // Register the channel with the system; you can't change the importance
//// or other notification behaviors after this
//            val notificationManager = getSystemService(
//                NotificationManager::class.java
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//}

