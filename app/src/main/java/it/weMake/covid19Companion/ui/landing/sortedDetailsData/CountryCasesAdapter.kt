package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.CountryCasesBinding
import it.weMake.covid19Companion.models.casesData.CountryCasesData
import it.weMake.covid19Companion.utils.SORT_BY_CONFIRMED
import it.weMake.covid19Companion.utils.SORT_BY_DEATHS
import it.weMake.covid19Companion.utils.SORT_BY_RECOVERED
import it.weMake.covid19Companion.utils.numberWithCommas

class CountryCasesAdapter(private val sortValue: String) : RecyclerView.Adapter<CountryCasesAdapter.Holder>() {

    private var countries = emptyList<CountryCasesData>() // Cached copy of country cases


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_cases, parent, false)

        return Holder(CountryCasesBinding.bind(view))
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(countries[position])
    }


    inner class Holder(private val binding: CountryCasesBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(current: CountryCasesData) {

            binding.countryCasesTV.text = current.displayName

            // USE THE STRING(sortValue) PASSED IN TO COMPARE WITH THE CONSTATNTS AND DISPLAY TEH LAYOUT ACCORDINGLY
            when(sortValue) {
                //check the when statement to see if it works by changing just the color or text of the sorted layout
                // we might still need to put the changes of colors and the rest in different functions
                SORT_BY_CONFIRMED -> populateCasesView(
                    R.color.black,
                    current.totalConfirmed?.numberWithCommas() ?: "Unknown"
                )

                SORT_BY_DEATHS -> populateCasesView(
                    R.color.red,
                    current.totalDeaths?.numberWithCommas() ?: "Unknown"
                )

                SORT_BY_RECOVERED -> populateCasesView(
                    R.color.colorPrimary,
                    current.totalRecovered?.numberWithCommas() ?: "Unknown"
                )

                else -> println("check code bro ")

            }

        }

        private fun populateCasesView(casesColor: Int, casesNumber: String) {
            binding.countryCasesValueTV.setTextColor(ContextCompat.getColor(itemView.context, casesColor))
            binding.countryCasesValueTV.text = casesNumber
        }

    }

    internal fun setCountries(countries: List<CountryCasesData>) {
        this.countries = countries
        notifyDataSetChanged()
    }

}