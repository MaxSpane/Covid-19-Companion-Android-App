package it.weMake.covid19Companion.ui.regionalStats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.RegionalStatsBinding
import it.weMake.covid19Companion.models.casesData.RegionCasesData
import it.weMake.covid19Companion.utils.numberWithCommas

class RegionalCasesStatsAdapter: RecyclerView.Adapter<RegionalCasesStatsAdapter.Holder>() {

    private val dataset = ArrayList<RegionCasesData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.regional_stats, parent, false)

        return Holder(RegionalStatsBinding.bind(view))
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataset[position])
    }

    fun refill(dataset: List<RegionCasesData>){
        this.dataset.clear()
        this.dataset.addAll(dataset)
        notifyDataSetChanged()
    }

    inner class Holder(private val itemBinding: RegionalStatsBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(itemData: RegionCasesData) {

            itemBinding.regionTV.text = itemData.displayName
            itemBinding.regionStatValueTV.text = itemData.totalConfirmed?.numberWithCommas() ?: "Unknown"

        }

    }

}