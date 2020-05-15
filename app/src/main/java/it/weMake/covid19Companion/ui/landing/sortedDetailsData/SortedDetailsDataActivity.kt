package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ActivitySortedDetailsDataBinding

class SortedDetailsDataActivity : AppCompatActivity() {
    lateinit var countryCasesStatsAdapter: CountryCasesAdapter
    private lateinit var binding: ActivitySortedDetailsDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySortedDetailsDataBinding.inflate(layoutInflater)
        val view = binding.root
        val countryCasesRecycler = binding.countryCasesRV
        countryCasesStatsAdapter =
            CountryCasesAdapter()
        countryCasesRecycler.adapter = countryCasesStatsAdapter
        setContentView(view)
    }
}
