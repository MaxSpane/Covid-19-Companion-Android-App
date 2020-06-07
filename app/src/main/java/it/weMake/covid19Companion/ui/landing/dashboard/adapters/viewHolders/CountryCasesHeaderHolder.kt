package it.weMake.covid19Companion.ui.landing.dashboard.adapters.viewHolders

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.HeaderDashboardCountryCasesBinding
import it.weMake.covid19Companion.utils.*


class CountryCasesHeaderHolder(
    private val binding: HeaderDashboardCountryCasesBinding,
    private val search: (searchQuery: String) -> Unit,
    private val sortBy: (sortBy: String) -> Unit
): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

    private var selectedTextView: TextView

    init {

        binding.searchET.addTextChangedListener {
            search(it.toString().trim())
        }

        selectedTextView = binding.sortByConfirmedTV

        binding.searchIV.setOnClickListener(this)
        binding.sortByConfirmedTV.setOnClickListener(this)
        binding.sortByRecoveredTV.setOnClickListener(this)
        binding.sortByDeathTV.setOnClickListener(this)
        binding.filterIV.setOnClickListener(this)
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.searchIV -> {

                if (binding.searchET.visibility == View.GONE){

                    if (binding.filterLL.isVisible)
                        closeFilter()

                    binding.searchET.show()
                    binding.searchIV.setImageResource(R.drawable.ic_close_ash_24dp)

                }else{
                    closeSearch()
                }

            }

            R.id.filterIV -> {

                if (binding.filterLL.visibility == View.GONE){

                    if (binding.searchET.isVisible)
                        closeSearch()

                    binding.filterLL.show()
                    binding.filterIV.setImageResource(R.drawable.ic_close_ash_24dp)
                }else{
                    closeFilter()
                }

            }

            R.id.sortByConfirmedTV -> {
                sortBy(SORT_BY_CONFIRMED)
                selectTextView(binding.sortByConfirmedTV)
            }

            R.id.sortByRecoveredTV -> {
                sortBy(SORT_BY_RECOVERED)
                selectTextView(binding.sortByRecoveredTV)
            }

            R.id.sortByDeathTV -> {
                sortBy(SORT_BY_DEATHS)
                selectTextView(binding.sortByDeathTV)
            }

        }

    }

    fun bind(lastUpdated: String) {
        binding.lastUpdatedValueTV.text = lastUpdated
    }

    private fun selectTextView(textView: TextView){
        setTextViewUnselected(selectedTextView)
        setTextViewSelected(textView)
        selectedTextView = textView
        closeFilter()
    }

    private fun setTextViewSelected(textView: TextView){
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark))
    }

    private fun setTextViewUnselected(textView: TextView){
        textView.setTypeface(textView.typeface, Typeface.NORMAL)
        textView.setTextColor(ContextCompat.getColor(itemView.context, R.color.grey))
    }

    private fun closeFilter(){
        binding.searchET.setText("")
        binding.filterLL.makeDisappear()
        binding.filterIV.setImageResource(R.drawable.ic_filter)

    }

    private fun closeSearch(){
        binding.searchET.makeDisappear()
        binding.searchIV.setImageResource(R.drawable.ic_search)
        binding.searchET.setText("")
    }

}
