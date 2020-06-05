package it.weMake.covid19Companion.ui.about.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemAppReleaseBinding
import it.weMake.covid19Companion.models.appReleases.AppRelease
import it.weMake.covid19Companion.utils.show

class AppReleasesAdapter(
    private val currentVersionNumber: Int
): RecyclerView.Adapter<AppReleasesAdapter.Holder>() {

    private val appReleases = ArrayList<AppRelease>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app_release, parent, false)

        return Holder(ItemAppReleaseBinding.bind(view))
    }

    override fun getItemCount(): Int = appReleases.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(appReleases[position])
    }

    fun refill(appReleases: List<AppRelease>){
        this.appReleases.clear()
        this.appReleases.addAll(appReleases)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemAppReleaseBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(itemData: AppRelease) {
            val context = itemView.context

            binding.versionNameTV.text = context.getString(R.string.placeholder_version_name, itemData.versionName)
            binding.versionDetails.text = itemData.versionDetails

            if (adapterPosition != appReleases.size && adapterPosition < currentVersionNumber){
                binding.versionNameTV.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                binding.versionDetails.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            }

        }

    }

}