package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.models.casesData.CountryCasesData
import it.weMake.covid19Companion.models.casesData.RegionCasesData
import it.weMake.covid19Companion.ui.regionalStats.RegionalStatsActivity
import it.weMake.covid19Companion.utils.*


class CountryCaseHolder(private val binding: ItemCountryCasesSummaryBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    private lateinit var itemData: CountryCasesData

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val context = itemView.context
        if (itemData.hasRegionalCasesData){
            RegionalStatsActivity.open(context, itemData)
        }else{
            showShortToast(context, context.getString(R.string.regional_data_unavailable))
        }
    }

    fun bind(itemData: CountryCasesData) {
        val context = itemView.context
        this.itemData = itemData

        binding.countryNameTV.text = itemData.displayName
        itemData.countryInfo.iso2.let {
            getFlagResourceId(itemView.context, it)?.let {flagResId ->
                binding.flagIV.setImageResource(flagResId) }

        }
        binding.confirmedValueTV.text = if(itemData.totalConfirmed == null){"Unknown"}else{itemData.totalConfirmed.numberWithCommas()}
        if (itemData.totalConfirmedDelta != null && itemData.totalConfirmedDelta != 0){
            binding.confirmedDeltaCP.show()
            val text = context.getString(R.string.new_cases_placeholder, itemData.totalConfirmedDelta.numberWithCommas())
            binding.confirmedDeltaCP.text = text
        }else{
            binding.confirmedDeltaCP.makeDisappear()
        }

        binding.recoveredValueTV.text = if(itemData.totalRecovered == null){"Unknown"}else{itemData.totalRecovered.numberWithCommas()}
        if (itemData.totalRecoveredDelta != null && itemData.totalRecoveredDelta != 0){
            binding.recoveredDeltaCP.show()
            binding.recoveredDeltaCP.text = context.getString(R.string.new_cases_placeholder, itemData.totalRecoveredDelta.numberWithCommas())
        }else{
            binding.recoveredDeltaCP.makeDisappear()
        }

        binding.deathsValueTV.text = if(itemData.totalDeaths == null){"Unknown"}else{itemData.totalDeaths.numberWithCommas()}
        if (itemData.totalDeathsDelta != null && itemData.totalDeathsDelta != 0){
            binding.deathsDeltaCP.show()
            binding.deathsDeltaCP.text = context.getString(R.string.new_cases_placeholder, itemData.totalDeathsDelta.numberWithCommas())
        }else{
            binding.deathsDeltaCP.makeDisappear()
        }

        if (itemData.hasRegionalCasesData){
            binding.regionalDataAvailabilityIV.setImageResource(R.drawable.ic_arrow_right)
        }else{
            binding.regionalDataAvailabilityIV.setImageResource(R.drawable.ic_block_black_24dp)
        }

    }

}
