package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.CountryCasesBinding
import it.weMake.covid19Companion.models.Country
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
        holder.bind(position)
    }



    inner class Holder(private val binding: CountryCasesBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(position: Int) {

                fun callConfirmedCasesView (){
                    binding.countryCasesValueTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                    val current = countries[position]
                    binding.countryCasesTV.text = current.displayName
                    binding.countryCasesValueTV.text = current.totalConfirmed?.numberWithCommas()

                }


                    fun callDeathCasesView (){
                        binding.countryCasesValueTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.deaths))
                        val current = countries[position]
                        binding.countryCasesTV.text = current.displayName
                        binding.countryCasesValueTV.text = current.totalDeaths?.numberWithCommas()

                    }


                fun callRecoveredCasesView (){
                    binding.countryCasesValueTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
                    val current = countries[position]
                    binding.countryCasesTV.text = current.displayName
                    binding.countryCasesValueTV.text = current.totalRecovered?.numberWithCommas()

                }
            // USE THE STRING(sortValue) PASSED IN TO COMPARE WITH THE CONSTATNTS AND DISPLAY TEH LAYOUT ACCORDINGLY

            when(sortValue) {
                //check the when statement to see if it works by changing just the color or text of the sorted layout
                // we might still need to put the changes of colors and the rest in different functions
                SORT_BY_CONFIRMED -> (callConfirmedCasesView())
                SORT_BY_DEATHS -> (callDeathCasesView())
                SORT_BY_RECOVERED -> (callRecoveredCasesView())
                else -> println("check code bro ")

            }

        }

    }

    internal fun setCountries(countries: List<CountryCasesData>) {
        this.countries = countries
        notifyDataSetChanged()
    }

}