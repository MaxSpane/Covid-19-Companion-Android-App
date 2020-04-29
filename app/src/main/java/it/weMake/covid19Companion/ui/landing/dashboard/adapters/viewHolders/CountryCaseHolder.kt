package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.models.CountryCasesData
import it.weMake.covid19Companion.utils.numberWithCommas
import it.weMake.covid19Companion.utils.show


class CountryCaseHolder(private val binding: ItemCountryCasesSummaryBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    init {

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
    }

    fun bind(item: CountryCasesData) {
        val context = itemView.context

        binding.countryNameTV.text = item.displayName
        item.iso2?.let {
            try {
                val resID: Int =
                    context.resources.getIdentifier("flag_${it.toLowerCase()}", "drawable", context.packageName)
                binding.flagIV.setImageResource(resID)
            }catch (e: Exception){
                Log.d("flag_shower", "$it ${item.displayName}")
            }
        }
        binding.confirmedValueTV.text = if(item.totalConfirmed == null){"Unknown"}else{item.totalConfirmed.numberWithCommas()}
        if (item.totalConfirmedDelta != null){
            binding.confirmedDeltaCP.show()

            binding.confirmedDeltaCP.text = context.getString(R.string.new_cases_placeholder, item.totalConfirmedDelta.numberWithCommas())
        }

        binding.recoveredValueTV.text = if(item.totalRecovered == null){"Unknown"}else{item.totalRecovered.numberWithCommas()}
        if (item.totalRecoveredDelta != null){
            binding.recoveredDeltaCP.show()
            binding.recoveredDeltaCP.text = context.getString(R.string.new_cases_placeholder, item.totalRecoveredDelta.numberWithCommas())
        }

        binding.deathsValueTV.text = if(item.totalDeaths == null){"Unknown"}else{item.totalDeaths.numberWithCommas()}
        if (item.totalDeathsDelta != null){
            binding.deathsDeltaCP.show()
            binding.deathsDeltaCP.text = context.getString(R.string.new_cases_placeholder, item.totalDeathsDelta.numberWithCommas())
        }

    }

}
