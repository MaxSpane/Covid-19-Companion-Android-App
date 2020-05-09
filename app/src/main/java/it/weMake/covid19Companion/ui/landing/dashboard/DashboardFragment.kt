package it.weMake.covid19Companion.ui.landing.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.commons.Error
import it.weMake.covid19Companion.commons.Loading
import it.weMake.covid19Companion.commons.Success
import dagger.android.support.DaggerFragment
import it.weMake.covid19Companion.databinding.FragmentDashboardBinding
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.DashboardAdapter
import it.weMake.covid19Companion.utils.*
import javax.inject.Inject

class DashboardFragment : DaggerFragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel: DashboardViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentDashboardBinding
    lateinit var dashboardAdapter: DashboardAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        dashboardAdapter = DashboardAdapter(search)

        binding.dashboardRV.adapter = dashboardAdapter

        attachObservers()

        viewModel.updateCasesData()

        return binding.root
    }

    override fun onClick(v: View) {
    }

    private fun attachObservers(){

        viewModel.casesDataLastUpdated.observe(viewLifecycleOwner, Observer {
            if(it == 0L){
                dashboardAdapter.setLastUpdated("Never")
            }else {
                dashboardAdapter.setLastUpdated(it.getTimeFromToday())
            }
        })

        viewModel.globalCasesData.observe(viewLifecycleOwner, Observer {
            it?.let {
                dashboardAdapter.setGlobalCasesData(it)
            }
        })

        viewModel.pagedCountriesCasesData.observe(viewLifecycleOwner, Observer {
            dashboardAdapter.addPage(it)
        })

        binding.dashboardRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dashboardAdapter.itemCount != 3){
                    if(dy > 0){
                        Log.i("RecyclerView scrolled: ", "scroll up!")
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        if (layoutManager.findLastVisibleItemPosition() == 17)
                            viewModel.loadPage(dashboardAdapter.pageBottom + 1)
                    }
                    else{
                        Log.i("RecyclerView scrolled: ", "scroll down!")
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        if (layoutManager.findFirstVisibleItemPosition() == 3 && dashboardAdapter.pageTop != 0)
                            viewModel.loadPage(dashboardAdapter.pageTop - 1)
                    }
                }

            }

        })

        viewModel.loadPage(0, 20)

        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> binding.dashboardSRL.isRefreshing = false
                is Error -> {
                    binding.dashboardSRL.isRefreshing = false
                    showLongToast(requireContext(), "Could not update Cases Data. Please try again.")
                }
                is Loading -> binding.dashboardSRL.isRefreshing = true
            }
        })

        binding.dashboardSRL.setOnRefreshListener { viewModel.updateCasesData() }


        viewModel.pagedSearchCountriesCasesData.observe(viewLifecycleOwner, Observer {
            dashboardAdapter.refill(it)
        })

    }

    private val search = fun (searchQuery: String){
        if (searchQuery.isEmpty()){
            dashboardAdapter.refill(viewModel.pagedCountriesCasesData.value!!)
        }else{
            viewModel.pagedSearch("%$searchQuery%", 0, 20)
        }
    }

}
