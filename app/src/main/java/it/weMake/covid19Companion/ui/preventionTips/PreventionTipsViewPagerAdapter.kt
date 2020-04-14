package it.weMake.covid19Companion.ui.preventionTips

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PreventionTipsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {


//    // create 5 lists each containing 3 strings for the preventiontiptitle, preventiontip and prevrentiontipwhy
    // and get a reerencce to them in the oncreatefragment method

    // can thge strings of list be done in another place ? another method or class


    var PreventionTipList = listOf(
            PreventionTip( "Wash your hands frequently","Regularly and thoroughly clean your hands with an alcohol-based\n" +
            "hand rub or wash them with soap and water.","Washing your hands with soap and water or using alcohol-based\n" +
            "hand rub kills viruses that may  be on your hands."),
            PreventionTip( "Maintain social distancing","Maintain at least 1 metre (3 feet) distance between yourself and " +
            "anyone who is coughing or sneezing.","When someone coughs or sneezes they spray small liquid droplets from" +
            "their nose or mouth which may contain virus. If you are too close, you can breathe in the droplets, " +
            "including the COVID-19 virus if the person coughing has the disease."),
            PreventionTip( "Avoid touching eyes, nose and mouth","Regularly and thoroughly clean your hands with an alcohol-based\n" +
            "hand rub or wash them with soap and water.","Washing your hands with soap and water or using alcohol-based\n" +
            "hand rub kills viruses that may  be on your hands."),
            PreventionTip( "Practice respiratory hygiene","Make sure you, and the people around you, follow good respiratory hygiene." +
            "This means covering your mouth and nose with your bent elbow or tissue when you cough or sneeze. Then dispose of the used tissue immediately.\n",
            "Droplets spread virus. By following good respiratory hygiene you protect the people around you from viruses such as cold, flu and COVID-19."),
            PreventionTip( "Stay informed and follow advice given by your healthcare provider",
            "Stay informed on the latest developments about COVID-19. Follow advice given by your healthcare provider," +
            " your national and local public health authority or your employer on how to protect yourself and others from COVID-19.",
            "National and local authorities will have the most up to date information on whether COVID-19 is spreading in your area. " +
            "They are best placed to advise on what people in your area should be doing to protect themselves.")

    )


        override fun getItemCount(): Int = PreventionTipList.size

        override fun createFragment(position: Int): Fragment {
        val item = PreventionTipList[position]
           return  PreventionTipFragment.newInstance(item.preventionTipTitle,item.preventionTip,item.preventionTipWhy)

    }
}

            data  class PreventionTip (val preventionTipTitle : String, val preventionTip : String, val preventionTipWhy : String)