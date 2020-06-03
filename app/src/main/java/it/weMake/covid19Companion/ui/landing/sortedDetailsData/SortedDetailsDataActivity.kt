package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ActivitySortedDetailsDataBinding
import it.weMake.covid19Companion.utils.SORT_BY_CONFIRMED
import it.weMake.covid19Companion.utils.SORT_BY_DEATHS
import it.weMake.covid19Companion.utils.SORT_BY_RECOVERED
import it.weMake.covid19Companion.utils.numberWithCommas
import javax.inject.Inject


class SortedDetailsDataActivity : DaggerAppCompatActivity() {

    lateinit var countryCasesStatsAdapter: CountryCasesAdapter
    private lateinit var binding: ActivitySortedDetailsDataBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel: SortedDetailsDataViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortedDetailsDataBinding.inflate(layoutInflater)
        val view = binding.root

        // Create an Intent and get the string extra and pass it to the viewmodel
        val intent = intent
        val sortValue = intent.getStringExtra(SORT_VALUE_EXTRA)
        viewModel.getCountryLiveData(sortValue)


        val countryCasesRecycler = binding.countryCasesRV
        countryCasesStatsAdapter =
            CountryCasesAdapter(sortValue)
        countryCasesRecycler.adapter = countryCasesStatsAdapter

        setContentView(view)

        viewModel.countriesLiveData.observe(this, Observer {
            // Update the cached copy of the countries in the adapter.
            countryCasesStatsAdapter.setCountries(it)
        })
        // getting access to the global stats data and displaying when the different pages in the ux design are called

        viewModel.globalCasesData.observe(this, Observer {
            when(sortValue) {
                //display different layouts and data based on string value passed
                SORT_BY_CONFIRMED -> (callConfirmedCasesView(it.confirmed))
                SORT_BY_DEATHS-> (callDeathCasesView(it.deaths))
                SORT_BY_RECOVERED -> (callRecoveredCasesView(it.recovered))
                else -> println("check code bro ")
            }
        })

    }

    companion object {

        private const val SORT_VALUE_EXTRA = "sortValueExtra"

        fun open (context: Context, sortValue : String) {
            val intent = Intent(context, SortedDetailsDataActivity::class.java)
            intent.putExtra(SORT_VALUE_EXTRA, sortValue)
            context.startActivity(intent)
        }
    }

    fun callConfirmedCasesView ( globalconfirmedStats : Int){

        binding.detailsTitleTV.text = getString(R.string.total_confirmed)
        binding.detailsValueTV.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.detailsValueTV.text = globalconfirmedStats.numberWithCommas()
        binding.confirmedCasesTV.text = getString(R.string.confirmed_cases_by_country)
        binding.image.setImageResource(R.drawable.ic_cough)

    }

    fun callDeathCasesView (gloablDeathStats : Int){
        binding.detailsTitleTV.text = getString(R.string.total_deaths)
        binding.detailsValueTV.setTextColor(ContextCompat.getColor(this, R.color.red))
        binding.detailsValueTV.text = gloablDeathStats.numberWithCommas()
        binding.confirmedCasesTV.text = getString(R.string.deaths_by_country)
        binding.image.setImageResource(R.drawable.ic_vomit)
    }

    fun callRecoveredCasesView (globalRecoveredStats: Int){
        binding.detailsTitleTV.text = getString(R.string.total_recovered)
        binding.detailsValueTV.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        binding.detailsValueTV.text = globalRecoveredStats.numberWithCommas()
        binding.confirmedCasesTV.text = getString(R.string.recovered_by_country)
        binding.image.setImageResource(R.drawable.ic_mask)
    }

}
