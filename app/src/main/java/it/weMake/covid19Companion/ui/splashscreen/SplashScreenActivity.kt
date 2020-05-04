package it.weMake.covid19Companion.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.weMake.covid19Companion.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
