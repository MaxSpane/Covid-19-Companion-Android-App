package it.weMake.covid19Companion.ui.regionalStats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.RegionalStatsBinding

class RegionalCasesStatsAdapter(): RecyclerView.Adapter<RegionalCasesStatsAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.regional_stats, parent, false)

        return Holder(RegionalStatsBinding.bind(view))
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }



    inner class Holder(private val itemBinding: RegionalStatsBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(position: Int) {

                itemBinding.regionStatTV.text = "3,000"
                itemBinding.regionStatValueTV.text = "Lagos"


        }

    }

}