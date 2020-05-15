package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.CountryCasesBinding
import it.weMake.covid19Companion.databinding.RegionalStatsBinding

class CountryCasesAdapter(): RecyclerView.Adapter<CountryCasesAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_cases, parent, false)

        return Holder(CountryCasesBinding.bind(view))
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }



    inner class Holder(private val itemBinding: CountryCasesBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(position: Int) {

                itemBinding.countryCasesValueTV.text = "3,000"
                itemBinding.countryCasesTV.text = "Lagos"


        }

    }

}