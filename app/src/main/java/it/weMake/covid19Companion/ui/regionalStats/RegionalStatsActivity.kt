package it.weMake.covid19Companion.ui.regionalStats

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import it.weMake.covid19Companion.databinding.ActivityRegionalStatsBinding
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.RegionalCasesStatsAdapter

class RegionalStatsActivity : AppCompatActivity() {
    lateinit var regionalCasesStatsAdapter: RegionalCasesStatsAdapter
    private lateinit var binding: ActivityRegionalStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true);
        binding = ActivityRegionalStatsBinding.inflate(layoutInflater)
        val view = binding.root
        val regionsRecycler = binding.regionStatsRV
        regionalCasesStatsAdapter = RegionalCasesStatsAdapter()
       regionsRecycler.adapter = regionalCasesStatsAdapter
        setContentView(view)
    }

}
