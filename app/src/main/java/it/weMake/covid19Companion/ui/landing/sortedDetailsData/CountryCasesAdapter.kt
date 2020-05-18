package it.weMake.covid19Companion.ui.landing.sortedDetailsData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.CountryCasesBinding
import it.weMake.covid19Companion.utils.SORT_BY_CONFIRMED_CASES
import it.weMake.covid19Companion.utils.SORT_BY_DEATHS
import it.weMake.covid19Companion.utils.SORT_BY_RECOVERED

class CountryCasesAdapter(private val sortValue: String) : RecyclerView.Adapter<CountryCasesAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_cases, parent, false)

        return Holder(CountryCasesBinding.bind(view))
    }

    override fun getItemCount(): Int = 10

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
                }


                    fun callDeathCasesView (){
                        binding.countryCasesValueTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.deaths))

                    }


                fun callRecoveredCasesView (){
                    binding.countryCasesValueTV.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))

                }
            // USE THE STRING(sortValue) PASSED IN TO COMPARE WITH THE CONSTATNTS AND DISPLAY TEH LAYOUT ACCORDINGLY

            when(sortValue) {
                //check the when statement to see if it works by changing just the color or text of the sorted layout
                // we might still need to put the changes of colors and the rest in different functions
                SORT_BY_CONFIRMED_CASES -> (callConfirmedCasesView())
                SORT_BY_DEATHS -> (callDeathCasesView())
                SORT_BY_RECOVERED -> (callRecoveredCasesView())
                else -> println("check code bro ")

            }




        }

    }

}