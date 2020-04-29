package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.databinding.WhoHandHygieneBrochureBinding
import it.weMake.covid19Companion.utils.hide
import it.weMake.covid19Companion.utils.makeDisappear
import it.weMake.covid19Companion.utils.show


class WHOHandHygieneBrochureHolder(
    private val binding: WhoHandHygieneBrochureBinding,
    private val attemptDownloadHandHygienePDF: () -> Unit
): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    init {

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        attemptDownloadHandHygienePDF()
    }

    fun bind(isDownloading: Boolean) {
        if (isDownloading){
            binding.handHygienePB.show()
        }else{
            binding.handHygienePB.makeDisappear()
        }
    }

}
