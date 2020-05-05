package it.weMake.covid19Companion.ui.screeningTool.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemOptionBinding
import it.weMake.covid19Companion.databinding.ItemPreventionTipBinding
import it.weMake.covid19Companion.models.preventionTips.PreventionTip
import it.weMake.covid19Companion.models.screeningTool.QuestionItem
import it.weMake.covid19Companion.models.screeningTool.request.Evidence
import it.weMake.covid19Companion.utils.*

class QuestionItemsAdapter: RecyclerView.Adapter<QuestionItemsAdapter.Holder>() {

    private val dataset = ArrayList<QuestionItem>()
    private var questionType = QUESTION_TYPE_GROUP_MULTIPLE
    private val selectedItems = ArrayList<Int>()
    private var selectedViewHolder: QuestionItemsAdapter.Holder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false)

        return Holder(ItemOptionBinding.bind(view))
    }

    override fun getItemCount(): Int =
        if (questionType == QUESTION_TYPE_SINGLE){
            2
        }else{
            dataset.size
        }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (questionType == QUESTION_TYPE_SINGLE){
            holder.bindYesNo()
        }else{
            holder.bind(dataset[position])
        }
    }

    fun refill(questionType: String, dataset: List<QuestionItem>){
        selectedItems.clear()
        selectedViewHolder = null
        this.questionType = questionType
        this.dataset.clear()
        this.dataset.addAll(dataset)
        notifyDataSetChanged()
    }

    fun getEvidence(): List<Evidence> =
        when(questionType){

            QUESTION_TYPE_SINGLE -> {
                if (selectedItems.isEmpty()){
                    listOf()
                }else{
                    val choiceId = if(selectedItems[0] == 0){ "present" }else{ "absent" }
                    listOf(Evidence(dataset[0].id, choiceId))
                }
            }

            QUESTION_TYPE_GROUP_SINGLE -> {
                if (selectedItems.isEmpty()){
                    listOf()
                }else{
                    val id = dataset[selectedItems[0]].id
                    listOf(Evidence(id, "present"))
                }
            }

            QUESTION_TYPE_GROUP_MULTIPLE -> {
                val evidence = ArrayList<Evidence>()
                for (i in 0 until dataset.size){

                    val choiceId = if(selectedItems.contains(i)){ "present" }else{ "absent" }
                    evidence.add(Evidence(dataset[i].id, choiceId))

                }

                evidence
            }

            else -> listOf()

        }

    inner class Holder(private val binding: ItemOptionBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private lateinit var itemData: QuestionItem

        init {
            binding.root.setOnClickListener(this)
            binding.infoIV.setOnClickListener(this)
        }

        fun bind(itemData: QuestionItem) {
            this.itemData = itemData

            binding.selectedIV.setImageResource(R.drawable.ic_option_unselected)
            binding.optionTV.text = itemData.name
            itemData.explanation?.let {
                binding.infoIV.show()
            } ?: binding.infoIV.makeDisappear()

        }

        fun bindYesNo(){

            binding.selectedIV.setImageResource(R.drawable.ic_option_unselected)
            binding.infoIV.makeDisappear()
            if (adapterPosition == 0){
                binding.optionTV.setText(R.string.yes)
            }else{
                binding.optionTV.setText(R.string.no)
            }

        }

        fun deselect(){
            binding.selectedIV.setImageResource(R.drawable.ic_option_unselected)
        }

        override fun onClick(v: View) {
            val context = itemView.context
            
            if (v.id == R.id.infoIV){
                MaterialAlertDialogBuilder(context)
                    .setTitle(itemData.name)
                    .setMessage(itemData.explanation)
                    .setPositiveButton(context.getString(R.string.ok), null)
                    .create()
                    .show()
            }else{
                if (selectedItems.contains(adapterPosition)){
                    selectedItems.remove(element = adapterPosition)
                    binding.selectedIV.setImageResource(R.drawable.ic_option_unselected)

                    if(questionType == QUESTION_TYPE_SINGLE || questionType == QUESTION_TYPE_GROUP_SINGLE)
                        selectedViewHolder = null
                }else{

                    if (selectedItems.isNotEmpty() && (questionType == QUESTION_TYPE_SINGLE || questionType == QUESTION_TYPE_GROUP_SINGLE)){
//                        notifyItemChanged(selectedItems[0])
                        selectedItems.clear()

                        selectedViewHolder?.deselect()
                    }

                    selectedItems.add(adapterPosition)
                    binding.selectedIV.setImageResource(R.drawable.ic_option_selected)

                    if(questionType == QUESTION_TYPE_SINGLE || questionType == QUESTION_TYPE_GROUP_SINGLE)
                        selectedViewHolder = this
                }
            }
            
        }

    }

}