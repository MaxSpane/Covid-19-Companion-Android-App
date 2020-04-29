package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.HeaderDashboardCountryCasesBinding
import it.weMake.covid19Companion.databinding.HeaderGlobalStatsDashboardBinding
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.databinding.WhoHandHygieneBrochureBinding
import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.models.CountryCasesData
import it.weMake.covid19Companion.models.GlobalStats
import it.weMake.covid19Companion.models.PagedData
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders.CountryCaseHolder
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders.CountryCasesHeaderHolder
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders.GlobalStatsHeaderHolder
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders.WHOHandHygieneBrochureHolder


class DashboardAdapter(
    private val attemptDownloadHandHygienePDF: () -> Unit,
    private val search: (searchQuery: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var countryCases: ArrayList<CountryCasesData> = ArrayList()
    private var lastUpdated = "Never"
    private var globalCasesData: GlobalStats? = null
    private var isDownloadingHandHygieneBrochure = false

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
                    return WHOHandHygieneBrochureHolder(WhoHandHygieneBrochureBinding.bind(view), attemptDownloadHandHygienePDF)
                }

                VIEW_TYPE_COUNTRY_CASES_HEADER->{
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.header_dashboard_country_cases, parent, false)
                    return CountryCasesHeaderHolder(HeaderDashboardCountryCasesBinding.bind(view), search)
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
                    globalCasesData?.let { (viewHolder as GlobalStatsHeaderHolder).bind(it) }
                }

                VIEW_TYPE_WHO_HAND_HYGIENE_BROCHURE->{
                    (viewHolder as WHOHandHygieneBrochureHolder).bind(isDownloadingHandHygieneBrochure)
                }

                VIEW_TYPE_COUNTRY_CASES_HEADER->{
                    (viewHolder as CountryCasesHeaderHolder).bind(lastUpdated)
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

        val formerSize = countryCases.size
        countryCases.clear()
        notifyItemRangeRemoved(3, formerSize)
        countryCases.addAll(pagedData.data)
        notifyItemRangeInserted(3, countryCases.size)

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

    fun setGlobalCasesData(globalCasesData: GlobalStats){
        this.globalCasesData = globalCasesData
        if (itemCount != countryCases.size)
            notifyItemChanged(0)
    }

    fun setIsDownloadingHandHygieneBrochure(isDownloadingHandHygieneBrochure: Boolean){
        this.isDownloadingHandHygieneBrochure = isDownloadingHandHygieneBrochure
        if (itemCount != countryCases.size)
            notifyItemChanged(1)
    }

}