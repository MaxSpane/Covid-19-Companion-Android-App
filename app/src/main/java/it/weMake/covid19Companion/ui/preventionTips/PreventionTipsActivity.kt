package it.weMake.covid19Companion.ui.preventionTips

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ActivityPreventionTipsBinding
import it.weMake.covid19Companion.ui.landing.MainActivity
import it.weMake.covid19Companion.ui.landing.help.HelpViewModel
import javax.inject.Inject


class PreventionTipsActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityPreventionTipsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel: PreventionTipsViewModel by viewModels { viewModelFactory }

    private var numOfPreventionTips: Int? = null
    private lateinit var preventionTipsVPAdapter: PreventionTipsViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreventionTipsBinding.inflate(layoutInflater)


        val view = binding.root
        val preventionTipsViewPager = binding.preventionTipsVP
        val tabLayout = binding.preventionTipTL

        preventionTipsVPAdapter = PreventionTipsViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.preventionTipsVP.adapter = preventionTipsVPAdapter

        TabLayoutMediator(tabLayout, preventionTipsViewPager) { _, _ -> }.attach()

        attachObservers()
        //set the onclicklistener and put the changing of pages method in it
        binding.preventionTipsVP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if( position == numOfPreventionTips?.minus(1)){
                    binding.preventionTipNextTV.text = getString(R.string.get_started)
                }else {
                    binding.preventionTipNextTV.text = getString(R.string.next)
                }
                super.onPageSelected(position)
            }
        })
        binding.preventionTipNextTV.setOnClickListener {

            if (binding.preventionTipsVP.currentItem == numOfPreventionTips?.minus(1)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }else{
                binding.preventionTipsVP.setCurrentItem(binding.preventionTipsVP.currentItem + 1, true);
            }

        }

        setContentView(view)
    }

    private fun attachObservers() {
        viewModel.preventionTipsLiveData.observe(this, Observer {
            preventionTipsVPAdapter.fill(it)
            numOfPreventionTips = it.size
        })
    }

}
