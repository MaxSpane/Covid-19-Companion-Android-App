package it.weMake.covid19Companion.ui.preventionTips

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import it.weMake.covid19Companion.databinding.ActivityPreventionTipsBinding
import it.weMake.covid19Companion.ui.landing.MainActivity


class PreventionTipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreventionTipsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreventionTipsBinding.inflate(layoutInflater)


        val view = binding.root
        val preventionTipsViewPager = binding.preventionTipsVP
        val tabLayout = binding.preventionTipTL


        binding.preventionTipsVP.adapter = PreventionTipsViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, preventionTipsViewPager) { _, _ ->
        }.attach()
        //set the onclicklistener and put the changing of pages method in it
        binding.preventionTipsVP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if( position == 4){
                    binding.preventionNext.text = "Get Started"


                }
                else
                {  binding.preventionNext.text = "Next"

                }
                super.onPageSelected(position)
            }
        })
        binding.preventionNext.setOnClickListener {

            if (binding.preventionTipsVP.getCurrentItem() == 4) {
                binding.preventionNext.text = "Get Started"
            }
            if (binding.preventionTipsVP.getCurrentItem() == 4) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            } else
            {  binding.preventionNext.text = "Next"
            }
            binding.preventionTipsVP.setCurrentItem(binding.preventionTipsVP.getCurrentItem() + 1, true);

        }

        setContentView(view)


    }
}
