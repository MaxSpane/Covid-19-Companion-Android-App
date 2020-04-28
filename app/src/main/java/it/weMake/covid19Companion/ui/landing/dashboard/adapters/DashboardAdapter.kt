package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.HeaderDashboardCountryCasesBinding
import it.weMake.covid19Companion.databinding.HeaderGlobalStatsDashboardBinding
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.databinding.WhoHandHygieneBrochureBinding
import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.models.CountryCasesData
import it.weMake.covid19Companion.models.PagedData
import it.weMake.covid19Companion.utils.numberWithCommas
import it.weMake.covid19Companion.utils.show


class DashboardAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var countryCases: ArrayList<CountryCasesData> = ArrayList()
    private var lastUpdated = "Never"
    private var globalCasesData: AreaCasesData? = null
    var pageTop = 0
    var pageBottom = 1

    private val VIEW_TYPE_GLOBAL_STATS_HEADER = 0
    private val VIEW_TYPE_WHO_HAND_HYGIENE_BROCHURE = 1
    private val VIEW_TYPE_COUNTRY_CASES_HEADER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (itemCount == countryCases.size + 3){
            when(viewType){

                VIEW_TYPE_GLOBAL_STATS_HEADER->{
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.header_global_stats_dashboard, parent, false)
                    return GlobalStatsHeaderHolder(HeaderGlobalStatsDashboardBinding.bind(view))
                }

                VIEW_TYPE_WHO_HAND_HYGIENE_BROCHURE->{
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.who_hand_hygiene_brochure, parent, false)
                    return WHOHandHygieneBrochureHolder(WhoHandHygieneBrochureBinding.bind(view))
                }

                VIEW_TYPE_COUNTRY_CASES_HEADER->{
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.header_dashboard_country_cases, parent, false)
                    return CountryCasesHeaderHolder(HeaderDashboardCountryCasesBinding.bind(view))
                }

                else->{
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_cases_summary, parent, false)
                    return CountryCaseHolder(ItemCountryCasesSummaryBinding.bind(view))
                }

            }
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country_cases_summary, parent, false)
        return CountryCaseHolder(ItemCountryCasesSummaryBinding.bind(view))
    }

    override fun getItemCount(): Int = if (pageTop == 0){countryCases.size + 3}else{countryCases.size}

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (itemCount == countryCases.size + 3){
            when(getItemViewType(position)){

                VIEW_TYPE_GLOBAL_STATS_HEADER->{
                    (viewHolder as GlobalStatsHeaderHolder).bind()
                    Log.d("onBindViewHolder", "$position")
                }

                VIEW_TYPE_WHO_HAND_HYGIENE_BROCHURE->{
                    (viewHolder as WHOHandHygieneBrochureHolder).bind()
                }

                VIEW_TYPE_COUNTRY_CASES_HEADER->{
                    (viewHolder as CountryCasesHeaderHolder).bind()
                }

                else->{
                    (viewHolder as CountryCaseHolder).bind(countryCases[position - 3])
                }

            }
        }else{
            (viewHolder as CountryCaseHolder).bind(countryCases[position])
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (itemCount == countryCases.size + 3){
            return position
        }

        return 4
    }

    fun refill(pagedData: PagedData<List<CountryCasesData>>){

        if(pagedData.data.isEmpty())
            return

        if (pagedData.page == 0){
            countryCases.clear()
            countryCases.addAll(pagedData.data)
            notifyDataSetChanged()
        }else{

            countryCases.addAll(pagedData.data)
            notifyItemRangeInserted(pagedData.page * 10, 10)
        }

    }

    fun addPage(pagedData: PagedData<List<CountryCasesData>>){

        if(pagedData.data.isEmpty())
            return

        when{

            pagedData.page == 0 && pagedData.data.size == 20 -> {
                countryCases.addAll(pagedData.data)
                notifyDataSetChanged()
            }

            pagedData.page < pageTop ->{
                countryCases.subList(10, countryCases.size).clear()
                notifyItemRangeRemoved(10, 10)
                countryCases.addAll(0, pagedData.data)
                if (pagedData.page == 0){
                    notifyItemRangeInserted(0, 13)
                }else{
                    notifyItemRangeInserted(0, 10)
                }
                pageBottom = pageTop
                pageTop = pagedData.page
            }

            pagedData.page > pageBottom ->{
                countryCases.subList(0, 10).clear()
                if (pageTop == 0){
                    notifyItemRangeRemoved(0, 13)
                }else{
                    notifyItemRangeRemoved(0, 10)
                }
                countryCases.addAll(10, pagedData.data)
                notifyItemRangeInserted(10, 10)
                pageTop = pageBottom
                pageBottom = pagedData.page
            }

        }

    }

    fun setLastUpdated(lastUpdated: String){
        this.lastUpdated = lastUpdated
        if (itemCount != countryCases.size)
            notifyItemChanged(2)
    }

    fun setGlobalCasesData(globalCasesData: AreaCasesData){
        this.globalCasesData = globalCasesData
        if (itemCount != countryCases.size)
            notifyItemChanged(0)
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

        private var globalCasesStatsAdapter = GlobalCasesStatsAdapter()

        private var handler = Handler()
        private val autoScrollDelayedTime: Long = 2500
        private val autoScrollCountryStatsRunnable: Runnable = Runnable {

            val lastVisibleItemPosition = (binding.casesStatsRV.layoutManager!! as LinearLayoutManager).findLastVisibleItemPosition()

            if (lastVisibleItemPosition != 2){
                binding.casesStatsRV.smoothScrollToPosition(lastVisibleItemPosition + 1)
            }else{
                binding.casesStatsRV.smoothScrollToPosition(0)
            }

            autoScrollCountryStatsDelayed()

        }

        init {
            itemView.setOnClickListener(this)
            binding.casesStatsRV.adapter = globalCasesStatsAdapter
        }

        override fun onClick(v: View?) {
        }

        fun bind() {
            val context = itemView.context

            globalCasesData?.let {
                globalCasesStatsAdapter.updateGlobalCasesData(it)
                autoScrollCountryStatsDelayed()
            }
        }

        private fun autoScrollCountryStatsDelayed(){
            handler.postDelayed(autoScrollCountryStatsRunnable, autoScrollDelayedTime)
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
            binding.lastUpdatedValueTV.text = lastUpdated
        }

    }

}