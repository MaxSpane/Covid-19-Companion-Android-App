package it.weMake.covid19Companion.ui.landing.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemCasesSummaryBinding
import it.weMake.covid19Companion.models.casesData.GlobalStats
import it.weMake.covid19Companion.ui.landing.sortedDetailsData.SortedDetailsDataActivity
import it.weMake.covid19Companion.utils.SORT_BY_CONFIRMED
import it.weMake.covid19Companion.utils.SORT_BY_DEATHS
import it.weMake.covid19Companion.utils.SORT_BY_RECOVERED
import it.weMake.covid19Companion.utils.numberWithCommas

class GlobalCasesStatsAdapter(): RecyclerView.Adapter<GlobalCasesStatsAdapter.Holder>() {

    private var globalCasesData: GlobalStats? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cases_summary, parent, false)

        return Holder(ItemCasesSummaryBinding.bind(view))
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    fun updateGlobalCasesData(globalCasesData: GlobalStats){
        this.globalCasesData = globalCasesData
        notifyDataSetChanged()
    }

    inner class Holder(private val itemBinding: ItemCasesSummaryBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            when(adapterPosition){

                0 -> SortedDetailsDataActivity.open(context, SORT_BY_CONFIRMED)

                1 -> SortedDetailsDataActivity.open(context, SORT_BY_RECOVERED)

                2 -> SortedDetailsDataActivity.open(context, SORT_BY_DEATHS)

            }
        }

        fun bind(position: Int) {

            when(position){

                0 -> {
                    itemBinding.casesSummaryIV.setImageResource(R.drawable.ic_cough)
                    itemBinding.casesSummaryTitleTV.setText(R.string.confirmed)
                    itemBinding.casesSummaryCV.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
                    itemBinding.casesSummaryTitleTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.grey))
                    itemBinding.casesSummaryNumberTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                    itemBinding.casesSummaryNumberTV.text = if (globalCasesData == null){"0"} else {
                        globalCasesData!!.confirmed.numberWithCommas()
                    }
                }

                1 -> {
                    itemBinding.casesSummaryIV.setImageResource(R.drawable.ic_mask)
                    itemBinding.casesSummaryTitleTV.setText(R.string.recovered)
                    itemBinding.casesSummaryCV.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
                    itemBinding.casesSummaryTitleTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    itemBinding.casesSummaryNumberTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    itemBinding.casesSummaryNumberTV.text = if (globalCasesData == null){"0"} else {
                        globalCasesData!!.recovered.numberWithCommas()
                    }
                }

                2 -> {
                    itemBinding.casesSummaryIV.setImageResource(R.drawable.ic_vomit)
                    itemBinding.casesSummaryTitleTV.setText(R.string.deaths)
                    itemBinding.casesSummaryCV.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.deaths))
                    itemBinding.casesSummaryTitleTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    itemBinding.casesSummaryNumberTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    itemBinding.casesSummaryNumberTV.text = if (globalCasesData == null){"0"} else {
                        globalCasesData!!.deaths.numberWithCommas()
                    }
                }

            }

        }

    }

}