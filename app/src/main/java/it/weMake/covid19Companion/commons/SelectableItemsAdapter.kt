package it.weMake.covid19Companion.commons

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

abstract class SelectableItemsAdapter<H: SelectableItemsAdapter<H>.Holder>(
    private var multipleSelection: Boolean = false
): RecyclerView.Adapter<H>() {

    protected val selectedItems = ArrayList<Int>()
    private var selectedViewHolder: SelectableItemsAdapter<H>.Holder? = null

    abstract inner class Holder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var itemData: QuestionItem

        abstract fun deselect()

        abstract fun select()

        override fun onClick(v: View) {
            val context = itemView.context

            if (selectedItems.contains(adapterPosition)){
                selectedItems.remove(element = adapterPosition)
                deselect()

                if(!multipleSelection)
                    selectedViewHolder = null
            }else{

                if (selectedItems.isNotEmpty() && !multipleSelection){
                    selectedItems.clear()
                    selectedViewHolder?.deselect()
                }

                selectedItems.add(adapterPosition)
                select()

                if(!multipleSelection)
                    selectedViewHolder = this
            }
            
        }

    }

}