package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.utils.numberWithCommas
import it.weMake.covid19Companion.utils.show

class CountryCasesAdapter(): RecyclerView.Adapter<CountryCasesAdapter.Holder>() {

    private var dataset: List<AreaCasesData> = ArrayList()
    private var filteredDataset: List<AreaCasesData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_cases_summary, parent, false)

        return Holder(ItemCountryCasesSummaryBinding.bind(view))
    }

    override fun getItemCount(): Int = filteredDataset.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(filteredDataset[position])
    }

    fun refill(dataset: List<AreaCasesData>){
        this.dataset = dataset
        filteredDataset = dataset
        notifyDataSetChanged()
    }

    inner class Holder(private val itemBinding: ItemCountryCasesSummaryBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(item: AreaCasesData) {
            val context = itemView.context
            
            itemBinding.countryNameTV.text = item.displayName

            itemBinding.confirmedValueTV.text = if(item.totalConfirmed == null){"Unknown"}else{item.totalConfirmed.numberWithCommas()}
            if (item.totalConfirmedDelta != null){
                itemBinding.confirmedDeltaCP.show()
                itemBinding.confirmedDeltaCP.text = context.getString(R.string.new_cases_placeholder, item.totalConfirmedDelta.numberWithCommas())
            }

            itemBinding.recoveredValueTV.text = if(item.totalRecovered == null){"Unknown"}else{item.totalRecovered.numberWithCommas()}
            if (item.totalRecoveredDelta != null){
                itemBinding.recoveredDeltaCP.show()
                itemBinding.recoveredDeltaCP.text = context.getString(R.string.new_cases_placeholder, item.totalRecoveredDelta.numberWithCommas())
            }

            itemBinding.deathsValueTV.text = if(item.totalDeaths == null){"Unknown"}else{item.totalDeaths.numberWithCommas()}
            if (item.totalDeathsDelta != null){
                itemBinding.deathsDeltaCP.show()
                itemBinding.deathsDeltaCP.text = context.getString(R.string.new_cases_placeholder, item.totalDeathsDelta.numberWithCommas())
            }

        }

    }

}