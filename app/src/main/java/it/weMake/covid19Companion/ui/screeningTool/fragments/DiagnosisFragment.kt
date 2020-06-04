package it.weMake.covid19Companion.ui.screeningTool.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment

import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.commons.Error
import it.weMake.covid19Companion.commons.Success
import it.weMake.covid19Companion.databinding.FragmentDiagnosisBinding
import it.weMake.covid19Companion.ui.screeningTool.ScreeningToolViewModel
import it.weMake.covid19Companion.ui.screeningTool.adapters.ObservationsAdapter
import it.weMake.covid19Companion.utils.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class DiagnosisFragment : DaggerFragment() {

    lateinit var binding: FragmentDiagnosisBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val activityViewModel: ScreeningToolViewModel by activityViewModels { viewModelFactory }

    private lateinit var observationsAdapter: ObservationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDiagnosisBinding.inflate(inflater, container, false)

        activityViewModel.setLoadingText("Getting Diagnosis")

        observationsAdapter = ObservationsAdapter()
        binding.observationsRV.adapter = observationsAdapter
        attachObservers()
        binding.tryAgainMB.setOnClickListener {
            binding.errorLL.makeDisappear()
            activityViewModel.getDiagnosis()
        }

        activityViewModel.getDiagnosis()
        return binding.root
    }

    private fun attachObservers() {
        activityViewModel.uiState.observe(viewLifecycleOwner, Observer {

            when(it){

                is Success -> binding.contentNSV.show()

                is Error -> binding.errorLL.show()

            }

        })

        activityViewModel.diagnosisLiveData.observe(viewLifecycleOwner, Observer {

            binding.resultTV.text = it.label
            binding.descriptionTV.text = it.description

            if (it.observations.isEmpty()){
                binding.noObservationsTV.show()
            }else{
                observationsAdapter.refill(it.observations)
                binding.observationsRV.show()
            }

            val diagnosisLevelColor = when(it.diagnosisLevel){

                //Green
                DIAGNOSIS_LEVEL_NO_RISK -> R.color.green

                //purple
                DIAGNOSIS_LEVEL_SELF_MONITORING -> R.color.purple

                //yellow
                DIAGNOSIS_LEVEL_SELF_QUARANTINE -> R.color.yellow

                //orange
                DIAGNOSIS_LEVEL_ISOLATION_CALL -> R.color.orange

                //blue
                DIAGNOSIS_LEVEL_CALL_DOCTOR -> R.color.blue

                //red
                DIAGNOSIS_LEVEL_ISOLATION_AMBULANCE -> R.color.red

                else -> R.color.black
            }

            binding.resultTV.setTextColor(ContextCompat.getColor(requireContext(), diagnosisLevelColor))

            getDrawableResourceId(requireContext(), it.diagnosisLevel)?.let {
                binding.resultIV.setImageResource(it)
            }

        })
    }

}
