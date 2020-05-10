package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.HeaderDashboardCountryCasesBinding
import it.weMake.covid19Companion.databinding.HeaderGlobalStatsDashboardBinding
import it.weMake.covid19Companion.databinding.ItemCountryCasesSummaryBinding
import it.weMake.covid19Companion.models.casesData.CountryCasesData
import it.weMake.covid19Companion.models.casesData.GlobalStats
import it.weMake.covid19Companion.models.PagedData
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders.CountryCaseHolder
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders.CountryCasesHeaderHolder
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders.GlobalStatsHeaderHolder


class DashboardAdapter(
    private val search: (searchQuery: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var countryCases: ArrayList<CountryCasesData> = ArrayList()
    private var lastUpdated = "Never"
    private var globalCasesData: GlobalStats? = null
    private var isDownloadingHandHygieneBrochure = false
    private var userCountryCasesData: CountryCasesData? = null
    private var isUserSearching = false

    var pageTop = 0
    var pageBottom = 1

    private val VIEW_TYPE_GLOBAL_STATS_HEADER = 0
    private val VIEW_TYPE_COUNTRY_CASES_HEADER = 1
    private val VIEW_TYPE_USER_COUNTRY_CASES = 2
    private val VIEW_TYPE_COUNTRY_CASES = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (itemCount == countryCases.size + VIEW_TYPE_COUNTRY_CASES){
            when(viewType){

                VIEW_TYPE_GLOBAL_STATS_HEADER->{
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.header_global_stats_dashboard, parent, false)
                    return GlobalStatsHeaderHolder(HeaderGlobalStatsDashboardBinding.bind(view))
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

    override fun getItemCount(): Int = if (pageTop == 0){countryCases.size + VIEW_TYPE_COUNTRY_CASES}else{countryCases.size}

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (itemCount == countryCases.size + VIEW_TYPE_COUNTRY_CASES){
            when(getItemViewType(position)){

                VIEW_TYPE_GLOBAL_STATS_HEADER->{
                    globalCasesData?.let { (viewHolder as GlobalStatsHeaderHolder).bind(it) }
                }

                VIEW_TYPE_COUNTRY_CASES_HEADER->{
                    (viewHolder as CountryCasesHeaderHolder).bind(lastUpdated)
                }

                else->{
                    if(countryCases.isNotEmpty())
                        (viewHolder as CountryCaseHolder).bind(countryCases[position - VIEW_TYPE_USER_COUNTRY_CASES])
                }

            }
        }else{
            (viewHolder as CountryCaseHolder).bind(countryCases[position])
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (itemCount == countryCases.size + VIEW_TYPE_COUNTRY_CASES){
            return position
        }

        return VIEW_TYPE_COUNTRY_CASES
    }

    fun refill(pagedData: PagedData<List<CountryCasesData>>, isUserSearching: Boolean = false){

        this.isUserSearching = isUserSearching
        val formerSize = countryCases.size
        countryCases.clear()
        if (!isUserSearching && userCountryCasesData != null){
            countryCases.add(0, userCountryCasesData!!)
        }
        notifyItemRangeRemoved(VIEW_TYPE_USER_COUNTRY_CASES, formerSize)
        countryCases.addAll(pagedData.data)
        notifyItemRangeInserted(VIEW_TYPE_COUNTRY_CASES, countryCases.size)

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
                    countryCases.add(0, userCountryCasesData!!)
                    notifyItemRangeInserted(0, 10 + VIEW_TYPE_COUNTRY_CASES)
                }else{
                    notifyItemRangeInserted(0, 10)
                }
                pageBottom = pageTop
                pageTop = pagedData.page
            }

            pagedData.page > pageBottom ->{
                countryCases.subList(0, 10).clear()
                if (pageTop == 0){
                    notifyItemRangeRemoved(0, 10 + VIEW_TYPE_COUNTRY_CASES)
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
            notifyItemChanged(VIEW_TYPE_COUNTRY_CASES_HEADER)
    }

    fun setGlobalCasesData(globalCasesData: GlobalStats){
        this.globalCasesData = globalCasesData
        if (itemCount != countryCases.size)
            notifyItemChanged(VIEW_TYPE_GLOBAL_STATS_HEADER)
    }

    fun setUserCountryCasesData(userCountryCasesData: CountryCasesData){
        this.userCountryCasesData = userCountryCasesData
        if (itemCount != countryCases.size){
            countryCases.add(0, userCountryCasesData)
            notifyItemChanged(VIEW_TYPE_USER_COUNTRY_CASES)
        }
    }

}