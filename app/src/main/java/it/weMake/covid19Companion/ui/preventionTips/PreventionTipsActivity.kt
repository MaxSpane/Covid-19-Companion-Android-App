package it.weMake.covid19Companion.ui.preventionTips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ActivityPreventionTipsBinding
import kotlinx.android.synthetic.main.activity_prevention_tips.view.*

class PreventionTipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreventionTipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreventionTipsBinding.inflate(layoutInflater)
        // get a reference to the preventiontipnext textview
        // below is a reference using findviewbyid but we want to use binfding :(
        //  val tv_click_me = findViewById(R.id.tv_click_me) as TextView

        val view = binding.root

        //set the onclicklistener and put the changing of pages method in it

        binding.preventionNext.setOnClickListener {
            binding.preventionTipsVP.setCurrentItem(binding.preventionTipsVP.getCurrentItem() + 1, true);
            }
        setContentView(view)

        binding.preventionTipsVP.adapter = PreventionTipsViewPagerAdapter(supportFragmentManager, lifecycle)

    }
}
