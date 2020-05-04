package it.weMake.covid19Companion.ui.landing.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val switch1 = fragmentBinding.remTog2IV
        val switch2 = fragmentBinding.remTog3IV


            switch1.setOnClickListener {
                if (switch1.isChecked) {
                    openDialog()
                }
            }
            switch2.setOnClickListener {
                if (switch2.isChecked) {
                    openDialog2()
                }
            }
        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return fragmentBinding.root
    }

    fun setIntervalWashHand(hour: Int, minutes : Int){
        val switch1 = fragmentBinding.remTog2IV

         if (hour == 0 && minutes == 0   )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands "
             !switch1.isChecked
        }
       else  if (hour < 1 && minutes > 1   )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands every $minutes minutes"

        }
        else if (hour < 1 && minutes == 1   )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands every $minutes minute"

        }
        else if (hour == 1  && minutes == 0 )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands every $hour hour"

        }
        else if (hour > 1  && minutes == 0 )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands every $hour hours"

        }
        else if (hour == 1  && minutes == 1 )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands every $hour hour and $minutes minute"

        }
        else if (hour > 1  && minutes == 1 )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands every $hour hours and $minutes minute"

        }
        else if (hour  == 1  && minutes > 1 )   {
            fragmentBinding.remHandTime.text = "Remind me to wash my hands every $hour hour and $minutes minutes"

        }
        else {
             fragmentBinding.remHandTime.text =
                 "Remind me to wash my hands every $hour hours and $minutes minutes"
         }
    }

    fun setIntervalDrinkWater(hour: Int, minutes : Int){
        val switch1 = fragmentBinding.remTog3IV

        if (hour == 0 && minutes == 0   )   {
            fragmentBinding.remWater.text = "Remind me to drink water "
            !switch1.isChecked
        }
        else  if (hour < 1 && minutes > 1   )   {
            fragmentBinding.remWater.text = "Remind me to drink water every $minutes minutes"

        }
        else if (hour < 1 && minutes == 1   )   {
            fragmentBinding.remWater.text = "Remind me to drink water every $minutes minute"

        }
        else if (hour == 1  && minutes == 0 )   {
            fragmentBinding.remWater.text = "Remind me to drink water every $hour hour"

        }
        else if (hour > 1  && minutes == 0 )   {
            fragmentBinding.remWater.text = "Remind me to drink water every $hour hours"

        }
        else if (hour == 1  && minutes == 1 )   {
            fragmentBinding.remWater.text = "Remind me to drink water every $hour hour and $minutes minute"

        }
        else if (hour > 1  && minutes == 1 )   {
            fragmentBinding.remWater.text = "Remind me to drink water every $hour hours and $minutes minute"

        }
        else if (hour  == 1  && minutes > 1 )   {
            fragmentBinding.remWater.text = "Remind me to drink water every $hour hour and $minutes minutes"

        }
        else {
            fragmentBinding.remWater.text =
                "Remind me to drink water every $hour hours and $minutes minutes"
        }
    }


    fun openDialog() {
        val reminderDialog = ReminderDialog{value, value2->
            setIntervalWashHand(value, value2)
        }
        reminderDialog.show(childFragmentManager, "example dialog")
    }
    fun openDialog2() {
        val reminderDialog = ReminderDialog{value, value2->
            setIntervalDrinkWater(value, value2)
        }
        reminderDialog.show(childFragmentManager, "example dialog")
    }


}

