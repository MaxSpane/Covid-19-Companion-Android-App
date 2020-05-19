package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ActivitySortedDetailsDataBinding
import it.weMake.covid19Companion.utils.SORT_BY_CONFIRMED
import it.weMake.covid19Companion.utils.SORT_BY_DEATHS
import it.weMake.covid19Companion.utils.SORT_BY_RECOVERED
import kotlinx.android.synthetic.main.country_cases.*


class SortedDetailsDataActivity : AppCompatActivity() {
    lateinit var countryCasesStatsAdapter: CountryCasesAdapter
    private lateinit var binding: ActivitySortedDetailsDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortedDetailsDataBinding.inflate(layoutInflater)
        val view = binding.root

        val intent = intent
        val sortValue = intent.getStringExtra(SORT_VALUE_EXTRA)
        // USE THE STRING PASSED IN TO COMPARE WITH THE CONSTATNTS AND DISPLAY TEH LAYOUT ACCORDINGLY
        when(sortValue) {
            //check the when statement to see if it works by changing just the color or text of the sorted layout
            // we might still need to put the changes of colors and the rest in different functions
            SORT_BY_CONFIRMED -> (callConfirmedCasesView())
            SORT_BY_DEATHS-> (callDeathCasesView())
            SORT_BY_RECOVERED -> (callRecoveredCasesView())
            else -> println("check code bro ")
        }


        val countryCasesRecycler = binding.countryCasesRV
        countryCasesStatsAdapter =
            CountryCasesAdapter(sortValue)
        countryCasesRecycler.adapter = countryCasesStatsAdapter
        setContentView(view)
    }
    companion object {

        private const val SORT_VALUE_EXTRA = "sortValueExtra"



        fun open (context: Context, sortValue : String) {
            val intent = Intent(context, SortedDetailsDataActivity::class.java)


            intent.putExtra(SORT_VALUE_EXTRA, sortValue)


           context.startActivity(intent)

        }
    }


    fun callConfirmedCasesView (){

        binding.detailsTitleTV.text = "Total Confirmed"
        binding.detailsValueTV.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.confirmedCasesTV.text = "Confirmed Cases by Country"
        binding.image.setImageResource(R.drawable.ic_cough)

            }


    fun callDeathCasesView (){
        binding.detailsTitleTV.text = "Total Deaths"
        binding.detailsValueTV.setTextColor(ContextCompat.getColor(this, R.color.deaths))
        binding.confirmedCasesTV.text = "Death by Country"
        binding.image.setImageResource(R.drawable.ic_vomit)



    }


    fun callRecoveredCasesView (){
        binding.detailsTitleTV.text = "Total Recovered"
        binding.detailsValueTV.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        binding.confirmedCasesTV.text = "Recovered by Country"
        binding.image.setImageResource(R.drawable.ic_mask)


    }
}
