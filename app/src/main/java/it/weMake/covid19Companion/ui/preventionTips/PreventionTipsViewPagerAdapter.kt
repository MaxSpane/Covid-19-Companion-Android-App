package it.weMake.covid19Companion.ui.preventionTips

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import it.weMake.covid19Companion.models.preventionTips.PreventionTip

class PreventionTipsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    private var preventionTips = ArrayList<PreventionTip>()

    override fun getItemCount(): Int = preventionTips.size

    override fun createFragment(position: Int): Fragment =
       PreventionTipFragment.newInstance(preventionTips[position])

    fun fill(preventionTips: List<PreventionTip>){
        this.preventionTips.addAll(preventionTips)
        notifyDataSetChanged()
    }

}
