package it.weMake.covid19Companion.ui.about.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemResourceBinding
import it.weMake.covid19Companion.models.sources.Source


class ResourcesAdapter(): RecyclerView.Adapter<ResourcesAdapter.Holder>() {

    private val sources = ArrayList<Source>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resource, parent, false)

        return Holder(ItemResourceBinding.bind(view))
    }

    override fun getItemCount(): Int = sources.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(sources[position])
    }

    fun refill(sources: List<Source>){
        this.sources.clear()
        this.sources.addAll(sources)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemResourceBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private lateinit var itemData: Source

        init {
            binding.linkIV.setOnClickListener(this)
        }

        fun bind(itemData: Source) {
            this.itemData = itemData

            binding.resourceNameTV.text = itemData.sourceName
            binding.resourceDescriptionTV.text = itemData.sourceDescription
            binding.resourceLocationTV.text = itemData.resources
        }

        override fun onClick(v: View) {
            val url = itemData.sourceExternalLink
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            itemView.context.startActivity(intent)
        }

    }

}