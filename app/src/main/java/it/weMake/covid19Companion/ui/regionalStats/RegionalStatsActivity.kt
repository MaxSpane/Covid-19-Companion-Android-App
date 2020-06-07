package it.weMake.covid19Companion.ui.regionalStats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.commons.Error
import it.weMake.covid19Companion.databinding.ActivityRegionalStatsBinding
import it.weMake.covid19Companion.models.casesData.CountryCasesData
import it.weMake.covid19Companion.utils.getFlagResourceId
import it.weMake.covid19Companion.utils.numberWithCommas
import it.weMake.covid19Companion.utils.showShortToast
import javax.inject.Inject


class RegionalStatsActivity : DaggerAppCompatActivity() {

    companion object{

        private const val EXTRA_COUNTRY_CASES_DATA = "country_cases_data"

        fun open(context: Context, countryCasesData: CountryCasesData){
            val intent = Intent(context, RegionalStatsActivity::class.java)
            intent.putExtra(EXTRA_COUNTRY_CASES_DATA, countryCasesData)
            context.startActivity(intent)
        }

    }

    lateinit var regionalCasesStatsAdapter: RegionalCasesStatsAdapter
    private lateinit var binding: ActivityRegionalStatsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel: RegionalStatsViewModel by viewModels { viewModelFactory }

    private lateinit var countryCasesData: CountryCasesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegionalStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countryCasesData = intent.getParcelableExtra(EXTRA_COUNTRY_CASES_DATA)!!
        viewModel.getAllCountryRegionsCasesData(countryCasesData.displayName)
        viewModel.updateCountryRegionsCasesData(countryCasesData.displayName)

        val regionsRecycler = binding.regionStatsRV
        val dividerItemDecoration = DividerItemDecoration(
            regionsRecycler.context,
            LinearLayoutManager.VERTICAL        )
        regionsRecycler.addItemDecoration(dividerItemDecoration)
        regionalCasesStatsAdapter =
            RegionalCasesStatsAdapter()
       regionsRecycler.adapter = regionalCasesStatsAdapter

        getFlagResourceId(this, countryCasesData.countryInfo.iso2)?.let { flagResId ->
            binding.countryFlagIV.setImageResource(flagResId)
        }

        binding.countryNameTV.text = countryCasesData.displayName
        binding.countryCasesNumberTV.text = countryCasesData.totalConfirmed?.numberWithCommas() ?: getString(R.string.unknown)
        binding.countryRecoveryNumberTV.text = countryCasesData.totalRecovered?.numberWithCommas() ?: getString(R.string.unknown)
        binding.countryDeathNumberTV.text = countryCasesData.totalDeaths?.numberWithCommas() ?: getString(R.string.unknown)
        binding.casesTodayValueTV.text = countryCasesData.totalConfirmedDelta?.numberWithCommas() ?: "0"
        binding.deathsTodayValueTV.text = countryCasesData.totalDeathsDelta?.numberWithCommas() ?: "0"
        binding.casesPerMillionValueTV.text = countryCasesData.casesPerOneMillion.toString()
        binding.deathsPerMillionValueTV.text = countryCasesData.deathsPerOneMillion.toString()

        binding.backButtonIV.setOnClickListener { finish() }

        attachObservers()
    }

    private fun attachObservers() {
        viewModel.countryRegionsCasesDataLiveData.observe(this, Observer {
            regionalCasesStatsAdapter.refill(it)
        })

        viewModel.uiState.observe(this, Observer {
            when (it) {
//                is Success -> binding.regionStatsSRL.isRefreshing = false
                is Error -> {
                    showShortToast(this, getString(R.string.error_update_regional_cases_data))
                }
//                is Loading -> binding.regionStatsSRL.isRefreshing = true
            }
        })

    }

}
