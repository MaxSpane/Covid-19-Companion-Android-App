package it.weMake.covid19Companion.ui.landing.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentDashboardBinding
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.CasesStatsAdapter
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.CountryCasesAdapter
import it.weMake.covid19Companion.utils.getTimeFromToday
import it.weMake.covid19Companion.utils.hide
import it.weMake.covid19Companion.utils.makeDisappear
import it.weMake.covid19Companion.utils.show
import javax.inject.Inject

class DashboardFragment : DaggerFragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val dashboardViewModel: DashboardViewModel by viewModels { viewModelFactory }

    lateinit var fragmentBinding: FragmentDashboardBinding
    lateinit var casesStatsAdapter: CasesStatsAdapter
    lateinit var countryCasesAdapter: CountryCasesAdapter

    private lateinit var handler: Handler
    private val autoScrollDelayedTime: Long = 2500
    private val autoScrollCountryStatsRunnable: Runnable = Runnable {

        val lastVisibleItemPosition = (fragmentBinding.casesStatsRV.layoutManager!! as LinearLayoutManager).findLastVisibleItemPosition()

        if (lastVisibleItemPosition != 2){
            fragmentBinding.casesStatsRV.smoothScrollToPosition(lastVisibleItemPosition + 1)
        }else{
            fragmentBinding.casesStatsRV.smoothScrollToPosition(0)
        }

        autoScrollCountryStatsDelayed()

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        fragmentBinding = FragmentDashboardBinding.inflate(inflater, container, false)

        handler = Handler()
        casesStatsAdapter = CasesStatsAdapter()
        countryCasesAdapter = CountryCasesAdapter()

        fragmentBinding.casesStatsRV.adapter = casesStatsAdapter
        fragmentBinding.casesRV.adapter = countryCasesAdapter

        dashboardViewModel.countryCasesLastUpdated.observe(viewLifecycleOwner, Observer {
            if(it == "Never"){
                fragmentBinding.lastUpdatedValueTV.text = it
            }else {
                fragmentBinding.lastUpdatedValueTV.text = it.getTimeFromToday()
            }
        })

        dashboardViewModel.casesStats.observe(viewLifecycleOwner, Observer {
            casesStatsAdapter.updateCasesStats(it)

            if (it.allConfirmedCases != 0){
                autoScrollCountryStatsDelayed()
            }

        })

        dashboardViewModel.filteredCountryCases.observe(viewLifecycleOwner, Observer {
            countryCasesAdapter.refill(it)
        })

        dashboardViewModel.updateCasesSummary()

        fragmentBinding.searchET.addTextChangedListener {
            dashboardViewModel.search(it.toString())
        }

        fragmentBinding.searchIV.setOnClickListener(this)

        return fragmentBinding.root
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.searchIV -> {

                if (fragmentBinding.searchET.visibility == View.GONE){

                    fragmentBinding.searchET.show()
                    fragmentBinding.searchIV.setImageResource(R.drawable.ic_close_ash_24dp)

                }else{

                    fragmentBinding.searchET.makeDisappear()
                    fragmentBinding.searchIV.setImageResource(R.drawable.ic_search)
                    fragmentBinding.searchET.setText("")

                }

            }

        }

    }

    private fun autoScrollCountryStatsDelayed(){
        handler.postDelayed(autoScrollCountryStatsRunnable, autoScrollDelayedTime)
    }

}
