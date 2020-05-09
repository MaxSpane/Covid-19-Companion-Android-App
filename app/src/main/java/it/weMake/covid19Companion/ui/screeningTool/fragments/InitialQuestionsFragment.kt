package it.weMake.covid19Companion.ui.screeningTool.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment

import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.commons.Error
import it.weMake.covid19Companion.commons.Loading
import it.weMake.covid19Companion.commons.Success
import it.weMake.covid19Companion.databinding.FragmentInitialQuestionsBinding
import it.weMake.covid19Companion.ui.screeningTool.ScreeningToolViewModel
import it.weMake.covid19Companion.utils.showLongToast
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class InitialQuestionsFragment : DaggerFragment(), View.OnClickListener {

    private lateinit var binding: FragmentInitialQuestionsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val activityViewModel: ScreeningToolViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInitialQuestionsBinding.inflate(inflater, container, false)

        activityViewModel.setShowBackground(true)
        binding.nextMB.setOnClickListener(this)
        attachObservers()
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.nextMB -> {

                if (!binding.maleRB.isChecked && !binding.femaleRB.isChecked){
                    showLongToast(requireContext(), getString(R.string.select_gender))
                }else if (binding.ageET.text.isEmpty()){
                    showLongToast(requireContext(), getString(R.string.enter_age))
                }else{
                    val gender = if (binding.maleRB.isChecked){ "male" } else { "female" }
                    val age = binding.ageET.text.toString().toInt()

                    activityViewModel.setLoadingText(getString(R.string.loading_question))
                    if(activityViewModel.requestFirstQuestion(gender, age))
                        findNavController().navigate(R.id.action_initialQuestionsFragment_to_questionFragment)

                }

            }

        }
    }

    private fun attachObservers(){
        activityViewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    activityViewModel.setUiStateResting()
                    findNavController().navigate(R.id.action_initialQuestionsFragment_to_questionFragment)
                }

                is Error -> showLongToast(requireContext(), getString(R.string.error_loading_question))
            }
        })
    }

}
