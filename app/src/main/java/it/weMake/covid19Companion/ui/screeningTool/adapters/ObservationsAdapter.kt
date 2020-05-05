package it.weMake.covid19Companion.ui.screeningTool.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemObservationBinding
import it.weMake.covid19Companion.databinding.ItemPreventionTipBinding
import it.weMake.covid19Companion.models.screeningTool.Observation

class ObservationsAdapter: RecyclerView.Adapter<ObservationsAdapter.Holder>() {

    private val observations = ArrayList<Observation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_observation, parent, false)

        return Holder(ItemObservationBinding.bind(view))
    }

    override fun getItemCount(): Int = observations.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(observations[position])
    }

    fun refill(observations: List<Observation>){
        this.observations.clear()
        this.observations.addAll(observations)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemObservationBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(itemData: Observation) {

            binding.observationTV.text = itemData.text

            if (itemData.isEmergency){
                binding.observationIV.setImageResource(R.drawable.ic_emergency)
            }else{
                binding.observationIV.setImageResource(R.drawable.ic_option_selected)
            }

        }

    }

}