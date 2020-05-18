package it.weMake.covid19Companion.ui.landing.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentSettingsBinding
import it.weMake.covid19Companion.ui.landing.sortedDetailsData.SortedDetailsDataActivity.Companion.open
import it.weMake.covid19Companion.utils.SORT_BY_DEATHS
import it.weMake.covid19Companion.utils.SORT_BY_RECOVERED
import javax.inject.Inject

//Make sure you're extending DaggerFragment instead of Fragment so Dagger knows how to inject parameters for us
class SettingsFragment : DaggerFragment()  {

    lateinit var fragmentBinding: FragmentSettingsBinding
    lateinit var reminderLocationAdapter: ReminderLocationAdapter

    //Dagger injects viewModelFactory for us
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //viewModelFactory provides the SettingsViewModel for us
    protected val viewModel: SettingsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        reminderLocationAdapter = ReminderLocationAdapter()
        fragmentBinding.remHandLocRV.adapter = reminderLocationAdapter


        fragmentBinding.remWashHandsS.setOnClickListener {
            //using the n switch button to check if the sortedDetailsData UX works
            if (fragmentBinding.remWashHandsS.isChecked) {
                open(requireContext(), SORT_BY_DEATHS)
            }
//            if (fragmentBinding.remWashHandsS.isChecked) {
//                openWashHandsDialog()
//            }else{
//                setIntervalWashHand(0)
//            }
        }
        fragmentBinding.remDrinkWaterS.setOnClickListener {
            //using the notification switch button to check if the sortedDetailsData UX works
            if (fragmentBinding.remDrinkWaterS.isChecked) {
                open(requireContext(), SORT_BY_RECOVERED)
            }
//            if (fragmentBinding.remDrinkWaterS.isChecked) {
//                openDrinkWaterDialog()
//            }else{
//                setIntervalDrinkWater(0)
//            }
       }


        val washHandsInterval = viewModel.getWashHandsInterval()
        updateUIIntervalWashHand(washHandsInterval)
        return fragmentBinding.root
    }

    private fun setIntervalWashHand(intervalInMinutes: Int){
        viewModel.setWashHandsInterval(intervalInMinutes)
        updateUIIntervalWashHand(intervalInMinutes)
    }

    private fun updateUIIntervalWashHand(intervalInMinutes: Int){
         if (intervalInMinutes == 0)   {
            fragmentBinding.remHandTime.text = getString(R.string.default_text_wash_hands_reminder)
             fragmentBinding.remWashHandsS.isChecked = false
        }else{
            fragmentBinding.remHandTime.text = getString(R.string.placeholder_wash_hands_reminder, convertIntervalToText(intervalInMinutes))
             fragmentBinding.remWashHandsS.isChecked = true
        }
    }

    private fun setIntervalDrinkWater(intervalInMinutes: Int){
        updateUIIntervalDrinkWater(intervalInMinutes)
    }

    private fun updateUIIntervalDrinkWater(intervalInMinutes: Int){
        if (intervalInMinutes == 0)   {
            fragmentBinding.remWater.text = getString(R.string.default_text_drink_water_reminder)
            fragmentBinding.remWashHandsS.isChecked = false
        }else{
            fragmentBinding.remWater.text = getString(R.string.placeholder_drink_water_reminder, convertIntervalToText(intervalInMinutes))
            fragmentBinding.remWashHandsS.isChecked = true
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

