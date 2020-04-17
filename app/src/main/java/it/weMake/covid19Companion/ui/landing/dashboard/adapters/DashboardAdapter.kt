package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.HeaderDashboardCountryCasesBinding
import it.weMake.covid19Companion.databinding.HeaderGlobalStatsDashboardBinding
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.databinding.WhoHandHygieneBrochureBinding
import it.weMake.covid19Companion.models.CountryCasesData
import it.weMake.covid19Companion.utils.numberWithCommas
import it.weMake.covid19Companion.utils.show


class DashboardAdapter(): RecyclerView.Adapter<DashboardAdapter.CountryCaseHolder>() {

    private var countryCases: List<CountryCasesData> = ArrayList()

    private val VIEW_TYPE_GLOBAL_STATS_HEADER = 0
    private val VIEW_TYPE_WHO_HAND_HYGIENE_BROCHURE = 1
    private val VIEW_TYPE_COUNTRY_CASES_HEADER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryCaseHolder {
        when(viewType){

//            VIEW_TYPE_GLOBAL_STATS_HEADER->{
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.header_global_stats_dashboard, parent, false)
//                return GlobalStatsHeaderHolder(HeaderGlobalStatsDashboardBinding.bind(view))
//            }
//
//            VIEW_TYPE_WHO_HAND_HYGIENE_BROCHURE->{
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.who_hand_hygiene_brochure, parent, false)
//                return WHOHandHygieneBrochureHolder(WhoHandHygieneBrochureBinding.bind(view))
//            }
//
//            VIEW_TYPE_COUNTRY_CASES_HEADER->{
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.header_dashboard_country_cases, parent, false)
//                return CountryCasesHeaderHolder(HeaderDashboardCountryCasesBinding.bind(view))
//            }

            else->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_cases_summary, parent, false)
                return CountryCaseHolder(ItemCountryCasesSummaryBinding.bind(view))
            }

        }
    }

    override fun getItemCount(): Int = countryCases.size

    override fun onBindViewHolder(viewHolder: CountryCaseHolder, position: Int) {

//        when(getItemViewType(position)){
//
//            VIEW_TYPE_GLOBAL_STATS_HEADER->{
//                (viewHolder as GlobalStatsHeaderHolder).bind()
//            }
//
//            VIEW_TYPE_WHO_HAND_HYGIENE_BROCHURE->{
//                (viewHolder as WHOHandHygieneBrochureHolder).bind()
//            }
//
//            VIEW_TYPE_COUNTRY_CASES_HEADER->{
//                (viewHolder as CountryCasesHeaderHolder).bind()
//            }
//
//            else->{
//                (viewHolder as CountryCaseHolder).bind(countryCases[position - 3])
//            }
//
//        }

        (viewHolder as CountryCaseHolder).bind(countryCases[position])
    }

    fun refill(dataset: List<CountryCasesData>){
        this.countryCases = dataset
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class CountryCaseHolder(private val binding: ItemCountryCasesSummaryBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

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

    inner class GlobalStatsHeaderHolder(private val binding: HeaderGlobalStatsDashboardBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind() {
            val context = itemView.context
        }

    }

    inner class WHOHandHygieneBrochureHolder(private val binding: WhoHandHygieneBrochureBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind() {
            val context = itemView.context
        }

    }

    inner class CountryCasesHeaderHolder(private val binding: HeaderDashboardCountryCasesBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind() {
            val context = itemView.context
        }

    }

}