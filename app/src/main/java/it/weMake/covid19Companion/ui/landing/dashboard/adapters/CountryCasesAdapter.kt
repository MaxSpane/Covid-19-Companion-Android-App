package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.models.CountryCases

class CountryCasesAdapter(): RecyclerView.Adapter<CountryCasesAdapter.Holder>() {

    private var dataset: List<CountryCases> = ArrayList()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_cases_summary, parent, false)

        return Holder(ItemCountryCasesSummaryBinding.bind(view))
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataset[position])
    }

    fun refill(dataset: List<CountryCases>){
        this.dataset = dataset
        notifyDataSetChanged()
    }

    inner class Holder(private val itemBinding: ItemCountryCasesSummaryBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(item: CountryCases) {
            val context = itemView.context
            
            itemBinding.countryNameTV.text = item.country

            if (item.newConfirmed == 0){
                itemBinding.confirmedValueTV.text = context.getString(R.string.cases_placeholder, item.totalConfirmed)
            }else{
                itemBinding.confirmedValueTV.text = context.getString(R.string.cases_plus_new_placeholder, item.totalConfirmed, item.newConfirmed)
            }

            if (item.newRecovered == 0){
                itemBinding.recoveredValueTV.text = context.getString(R.string.cases_placeholder, item.totalRecovered)
            }else{
                itemBinding.recoveredValueTV.text = context.getString(R.string.cases_plus_new_placeholder, item.totalRecovered, item.newRecovered)
            }

            if (item.newDeaths == 0){
                itemBinding.deathsValueTV.text = context.getString(R.string.cases_placeholder, item.totalDeaths)
            }else{
                itemBinding.deathsValueTV.text = context.getString(R.string.cases_plus_new_placeholder, item.totalDeaths, item.newDeaths)
            }

        }

    }

}