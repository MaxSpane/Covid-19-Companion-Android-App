package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.databinding.HeaderGlobalStatsDashboardBinding
import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.models.GlobalStats
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.GlobalCasesStatsAdapter


class GlobalStatsHeaderHolder(private val binding: HeaderGlobalStatsDashboardBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

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
    private var isAutoScrollRuning = false

    init {
        itemView.setOnClickListener(this)
        binding.casesStatsRV.adapter = globalCasesStatsAdapter
    }

    override fun onClick(v: View?) {
    }

    fun bind(globalCasesData: GlobalStats) {
        globalCasesStatsAdapter.updateGlobalCasesData(globalCasesData)

        if (globalCasesData.confirmed != 0 && !isAutoScrollRuning){
            autoScrollCountryStatsDelayed()
            isAutoScrollRuning = true
        }
    }

    private fun autoScrollCountryStatsDelayed(){
        handler.postDelayed(autoScrollCountryStatsRunnable, autoScrollDelayedTime)
    }

}
