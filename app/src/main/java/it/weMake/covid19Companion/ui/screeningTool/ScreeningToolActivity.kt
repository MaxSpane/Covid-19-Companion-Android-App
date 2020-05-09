package it.weMake.covid19Companion.ui.screeningTool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.DaggerAppCompatActivity
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.commons.Loading
import it.weMake.covid19Companion.databinding.ActivityScreeningToolBinding
import it.weMake.covid19Companion.utils.makeDisappear
import it.weMake.covid19Companion.utils.show
import javax.inject.Inject

class ScreeningToolActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel: ScreeningToolViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityScreeningToolBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreeningToolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        attachObservers()
    }

    override fun onBackPressed() {

        if (viewModel.showBackgroundLiveData.value!!){
            MaterialAlertDialogBuilder(this)
                .setMessage(R.string.exit_screening_tool_dialog_message)
                .setPositiveButton(getString(R.string.yes)){ _, _ -> finish()}
                .setNegativeButton(getString(R.string.no), null)
                .create()
                .show()
        }else{
            super.onBackPressed()
        }

    }

    private fun attachObservers() {
        viewModel.showBackgroundLiveData.observe(this, Observer {
            if (it){
                binding.backgroundIV.show()
            }else{
                binding.backgroundIV.makeDisappear()
            }
        })

        viewModel.loadingTextLiveData.observe(this, Observer {
            binding.loadingTV.text = it
        })

        viewModel.uiState.observe(this, Observer {
            if (it is Loading){
                binding.navHostFragment.makeDisappear()
                binding.loadingLL.show()
            }else{
                binding.loadingLL.makeDisappear()
                binding.navHostFragment.show()
            }
        })
    }
}
