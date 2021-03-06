package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.HeaderGlobalStatsDashboardBinding
import it.weMake.covid19Companion.models.casesData.GlobalStats
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.GlobalCasesStatsAdapter


class GlobalStatsHeaderHolder(private val binding: HeaderGlobalStatsDashboardBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    private var globalCasesStatsAdapter = GlobalCasesStatsAdapter()

    private var handler = Handler()
    private val autoScrollDelayedTime: Long = 2500
    private val autoScrollCountryStatsRunnable: Runnable = Runnable {

        val visibleItemPosition = (binding.casesStatsRV.layoutManager!! as LinearLayoutManager).findLastVisibleItemPosition()

        if (visibleItemPosition != 2){
            binding.casesStatsRV.smoothScrollToPosition(visibleItemPosition + 1)
        }else{
            binding.casesStatsRV.smoothScrollToPosition(0)
        }

        autoScrollCountryStatsDelayed()

    }
    private var isAutoScrollRuning = false

    init {
        itemView.setOnClickListener(this)
        binding.casesStatsRV.adapter = globalCasesStatsAdapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.casesStatsRV)
    }

    override fun onClick(v: View?) {
    }

    fun bind(
        globalCasesData: GlobalStats,
        username: String
    ) {
        globalCasesStatsAdapter.updateGlobalCasesData(globalCasesData)

        if (globalCasesData.confirmed != 0 && !isAutoScrollRuning){
            autoScrollCountryStatsDelayed()
            isAutoScrollRuning = true
        }

        binding.welcomeTextTV.text = itemView.context.getString(R.string.placeholder_hello_user, username)
    }

    private fun autoScrollCountryStatsDelayed(){
        handler.postDelayed(autoScrollCountryStatsRunnable, autoScrollDelayedTime)
    }

}
