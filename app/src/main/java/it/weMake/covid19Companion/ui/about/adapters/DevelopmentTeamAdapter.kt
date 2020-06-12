package it.weMake.covid19Companion.ui.about.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ItemResourceBinding
import it.weMake.covid19Companion.models.developmentTeam.TeamMember
import it.weMake.covid19Companion.models.sources.Source


class DevelopmentTeamAdapter(): RecyclerView.Adapter<DevelopmentTeamAdapter.Holder>() {

    private val teamMembers = ArrayList<TeamMember>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resource, parent, false)

        return Holder(ItemResourceBinding.bind(view))
    }

    override fun getItemCount(): Int = teamMembers.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(teamMembers[position])
    }

    fun refill(teamMembers: List<TeamMember>){
        this.teamMembers.clear()
        this.teamMembers.addAll(teamMembers)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemResourceBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private lateinit var itemData: TeamMember

        init {
            binding.linkIV.setOnClickListener(this)
        }

        fun bind(itemData: TeamMember) {
            this.itemData = itemData

            binding.resourceNameTV.text = itemData.role
            binding.resourceDescriptionTV.text = itemData.about
            binding.resourceLocationTV.text = itemData.name
        }

        override fun onClick(v: View) {
            val url = itemData.externalLink
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            itemView.context.startActivity(intent)
        }

    }

}