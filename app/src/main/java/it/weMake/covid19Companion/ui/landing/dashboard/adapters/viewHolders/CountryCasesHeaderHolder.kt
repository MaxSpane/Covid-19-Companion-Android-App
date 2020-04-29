package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.databinding.HeaderDashboardCountryCasesBinding


class CountryCasesHeaderHolder(private val binding: HeaderDashboardCountryCasesBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
    }

    fun bind(lastUpdated: String) {
        val context = itemView.context
        binding.lastUpdatedValueTV.text = lastUpdated
    }

}
