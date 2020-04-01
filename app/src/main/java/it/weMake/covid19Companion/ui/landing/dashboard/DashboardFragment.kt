package it.weMake.covid19Companion.ui.landing.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentDashboardBinding
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.CasesStatsAdapter
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.CountryCasesAdapter
import it.weMake.covid19Companion.utils.getTimeFromToday
import javax.inject.Inject

class DashboardFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val dashboardViewModel: DashboardViewModel by viewModels { viewModelFactory }

    lateinit var fragmentBinding: FragmentDashboardBinding
    lateinit var casesStatsAdapter: CasesStatsAdapter
    lateinit var countryCasesAdapter: CountryCasesAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        fragmentBinding = FragmentDashboardBinding.inflate(inflater, container, false)

        casesStatsAdapter = CasesStatsAdapter()
        countryCasesAdapter = CountryCasesAdapter()

        fragmentBinding.casesStatsRV.adapter = casesStatsAdapter
        fragmentBinding.casesRV.adapter = countryCasesAdapter

        dashboardViewModel.countryCasesLastUpdated.observe(viewLifecycleOwner, Observer {
            fragmentBinding.lastUpdatedValueTV.text = it.getTimeFromToday()
        })

        dashboardViewModel.casesStats.observe(viewLifecycleOwner, Observer {
            casesStatsAdapter.updateCasesStats(it)
        })

        dashboardViewModel.countryCases.observe(viewLifecycleOwner, Observer {
            countryCasesAdapter.refill(it)
        })

        dashboardViewModel.updateCasesSummary()

        return fragmentBinding.root
    }

}
