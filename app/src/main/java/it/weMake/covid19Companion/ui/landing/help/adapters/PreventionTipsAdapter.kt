package it.weMake.covid19Companion.ui.landing.help.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemPreventionTipBinding
import it.weMake.covid19Companion.models.preventionTips.PreventionTip

class PreventionTipsAdapter: RecyclerView.Adapter<PreventionTipsAdapter.Holder>() {

    private val preventionTips = ArrayList<PreventionTip>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prevention_tip, parent, false)

        return Holder(ItemPreventionTipBinding.bind(view))
    }

    override fun getItemCount(): Int = preventionTips.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(preventionTips[position])
    }

    fun refill(preventionTips: List<PreventionTip>){
        this.preventionTips.clear()
        this.preventionTips.addAll(preventionTips)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemPreventionTipBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(itemData: PreventionTip) {

            binding.preventionTipTitleTV.text = itemData.title
            binding.preventionTipTV.text = itemData.preventionTip
            binding.preventionTipWhyTV.text = itemData.preventionTipWhy

        }

    }

}