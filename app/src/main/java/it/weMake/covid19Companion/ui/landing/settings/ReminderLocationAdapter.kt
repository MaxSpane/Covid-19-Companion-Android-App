package it.weMake.covid19Companion.ui.landing.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ReminderLocationBinding
import it.weMake.covid19Companion.models.washHandsReminderLocations.WashHandsReminderLocation

class ReminderLocationAdapter(
    private val openLocationDetails: (washHandsReminderLocation: WashHandsReminderLocation)-> Unit,
    private val enableDisableWashHandsReminderLocation: (washHandsReminderLocation: WashHandsReminderLocation)-> Unit
): RecyclerView.Adapter<ReminderLocationAdapter.Holder>() {

    private val dataset = ArrayList<WashHandsReminderLocation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reminder_location, parent, false)

        return Holder(ReminderLocationBinding.bind(view))
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataset[position])
    }

    fun refill(dataset: List<WashHandsReminderLocation>){
        this.dataset.clear()
        this.dataset.addAll(dataset)
        notifyDataSetChanged()
    }



    inner class Holder(private val binding: ReminderLocationBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private lateinit var itemData: WashHandsReminderLocation

        init {
            binding.editIV.setOnClickListener(this)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (v.id == R.id.edit_IV)
                openLocationDetails(itemData)
        }

        fun bind(itemData: WashHandsReminderLocation) {
            this.itemData = itemData

            binding.remHandLocCB.text = itemData.name
            binding.remHandLocCB.isChecked = itemData.enabled

            binding.remHandLocCB.setOnCheckedChangeListener { _, isChecked ->
                itemData.enabled = isChecked
                enableDisableWashHandsReminderLocation(itemData)
            }
        }

    }

}