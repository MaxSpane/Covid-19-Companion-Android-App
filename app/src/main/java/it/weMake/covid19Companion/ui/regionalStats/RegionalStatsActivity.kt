package it.weMake.covid19Companion.ui.regionalStats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import it.weMake.covid19Companion.databinding.ActivityRegionalStatsBinding


class RegionalStatsActivity : AppCompatActivity() {
    lateinit var regionalCasesStatsAdapter: RegionalCasesStatsAdapter
    private lateinit var binding: ActivityRegionalStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegionalStatsBinding.inflate(layoutInflater)
        val view = binding.root
        val regionsRecycler = binding.regionStatsRV
        val dividerItemDecoration = DividerItemDecoration(
            regionsRecycler.context,
            LinearLayoutManager.VERTICAL        )
        regionsRecycler.addItemDecoration(dividerItemDecoration)
        regionalCasesStatsAdapter =
            RegionalCasesStatsAdapter()
       regionsRecycler.adapter = regionalCasesStatsAdapter
        setContentView(view)
    }

}
