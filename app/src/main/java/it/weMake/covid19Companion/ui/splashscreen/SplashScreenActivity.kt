package it.weMake.covid19Companion.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.HapticFeedbackConstants
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import it.weMake.covid19Companion.databinding.ActivitySplashScreenBinding
import it.weMake.covid19Companion.ui.landing.MainActivity
import it.weMake.covid19Companion.ui.preventionTips.PreventionTipsActivity
import it.weMake.covid19Companion.utils.ONE_SECOND_IN_MILLI
import javax.inject.Inject

class SplashScreenActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel: SplashScreenViewModel by viewModels { viewModelFactory }

    private var longPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dailyMotivationTV.text = viewModel.getDailyMotivation()
        val handler = Handler()
        val runnable = Runnable {
            if (!longPressed)
                observeUserCountryIso2()
        }

        handler.postDelayed(runnable, 2 * ONE_SECOND_IN_MILLI)

        binding.splashscreenCL.setOnLongClickListener {
            longPressed = true
            true
        }

        binding.splashscreenCL.setOnClickListener {
            observeUserCountryIso2()
        }
    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun observeUserCountryIso2(){
        viewModel.userCountryIso2LiveData.observe(this, Observer {

            if(it.isEmpty()){
                observeCountries()
            }else{
                openActivity(MainActivity::class.java)
            }

        })
    }

    private fun observeCountries(){
        viewModel.countriesLiveData.observe(this, Observer {countries->

            MaterialAlertDialogBuilder(this)
                .setTitle("Welcome Survivor, choose where your Loyalties lie.")
                .setItems(countries.map { it.name }.toTypedArray()) {_, which ->
                    viewModel.setUserCountryIso(countries[which].iso2!!)
                    openActivity(PreventionTipsActivity::class.java)
                }
                .setCancelable(false)
                .create()
                .show()
        })
    }

}
