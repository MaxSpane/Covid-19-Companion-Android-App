package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.models.CountryCasesData
import it.weMake.covid19Companion.utils.numberWithCommas
import it.weMake.covid19Companion.utils.show


class CountryCasesAdapter(): RecyclerView.Adapter<CountryCasesAdapter.Holder>() {

    private var dataset: List<CountryCasesData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_cases_summary, parent, false)

        return Holder(ItemCountryCasesSummaryBinding.bind(view))
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataset[position])
    }

    fun refill(dataset: List<CountryCasesData>){
        this.dataset = dataset
        notifyDataSetChanged()
    }

    inner class Holder(private val itemBinding: ItemCountryCasesSummaryBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(item: CountryCasesData) {
            val context = itemView.context
            
            itemBinding.countryNameTV.text = item.displayName
            item.iso2?.let {
                try {
                    val resID: Int =
                        context.resources.getIdentifier("flag_${it.toLowerCase()}", "drawable", context.packageName)
                    itemBinding.flagIV.setImageResource(resID)
                }catch (e: Exception){
                    Log.d("flag_shower", "$it ${item.displayName}")
                }
            }
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