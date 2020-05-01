package it.weMake.covid19Companion.ui.landing.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ReminderLocationBinding

class ReminderLocationAdapter(): RecyclerView.Adapter<ReminderLocationAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reminder_location, parent, false)

        return Holder(ReminderLocationBinding.bind(view))
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }



    inner class Holder(private val itemBinding: ReminderLocationBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(position: Int) {

                itemBinding.remHandLocTV.text = "14, God in Action Avenue, Olodi-Apapa."


        }

    }

}