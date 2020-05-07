package it.weMake.covid19Companion.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = Handler()
        val runnable = Runnable {

            val intent = if(viewModel.isFirstLaunch.value == true){
                Intent(this, PreventionTipsActivity::class.java)
            }else{
                Intent(this, MainActivity::class.java)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        handler.postDelayed(runnable, 2 * ONE_SECOND_IN_MILLI)
    }

}
