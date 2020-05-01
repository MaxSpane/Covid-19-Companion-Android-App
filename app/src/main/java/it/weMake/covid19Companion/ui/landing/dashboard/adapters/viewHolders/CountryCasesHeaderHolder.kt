package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.HeaderDashboardCountryCasesBinding
import it.weMake.covid19Companion.utils.makeDisappear
import it.weMake.covid19Companion.utils.show


class CountryCasesHeaderHolder(
    private val binding: HeaderDashboardCountryCasesBinding,
    private val search: (searchQuery: String) -> Unit
): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    init {

        binding.searchET.addTextChangedListener {
            search(it.toString())
        }

        binding.searchIV.setOnClickListener(this)
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.searchIV -> {

                if (binding.searchET.visibility == View.GONE){

                    binding.searchET.show()
                    binding.searchIV.setImageResource(R.drawable.ic_close_ash_24dp)

                }else{

                    binding.searchET.makeDisappear()
                    binding.searchIV.setImageResource(R.drawable.ic_search)
                    binding.searchET.setText("")

                }

            }

        }

    }

    fun bind(lastUpdated: String) {
        val context = itemView.context
        binding.lastUpdatedValueTV.text = lastUpdated
    }

}
