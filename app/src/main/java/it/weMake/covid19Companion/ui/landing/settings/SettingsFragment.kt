package it.weMake.covid19Companion.ui.landing.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


        fragmentBinding.remWashHandsS.setOnClickListener {
            if (fragmentBinding.remWashHandsS.isChecked) {
                openWashHandsDialog()
            }else{
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


}

