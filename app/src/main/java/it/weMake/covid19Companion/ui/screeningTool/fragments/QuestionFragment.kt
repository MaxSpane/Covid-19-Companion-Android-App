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
import it.weMake.covid19Companion.commons.Success
import it.weMake.covid19Companion.databinding.FragmentQuestionBinding
import it.weMake.covid19Companion.ui.screeningTool.ScreeningToolViewModel
import it.weMake.covid19Companion.ui.screeningTool.adapters.QuestionItemsAdapter
import it.weMake.covid19Companion.utils.QUESTION_TYPE_GROUP_MULTIPLE
import it.weMake.covid19Companion.utils.QUESTION_TYPE_GROUP_SINGLE
import it.weMake.covid19Companion.utils.QUESTION_TYPE_SINGLE
import it.weMake.covid19Companion.utils.showShortToast
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : DaggerFragment(), View.OnClickListener {

    private lateinit var binding: FragmentQuestionBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val activityViewModel: ScreeningToolViewModel by activityViewModels { viewModelFactory }

    private lateinit var questionItemsAdapter: QuestionItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionBinding.inflate(inflater, container, false)

        questionItemsAdapter = QuestionItemsAdapter()
        binding.optionsRV.adapter = questionItemsAdapter

        binding.nextMB.setOnClickListener(this)
        binding.backMB.setOnClickListener(this)
        attachObservers()
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.nextMB -> {
                val evidence = questionItemsAdapter.getEvidence()

                if (evidence.isEmpty()){
                    showShortToast(requireContext(), getString(R.string.no_option_selected))
                }else{
                    activityViewModel.nextQuestion(evidence)
                }
            }

            R.id.backMB -> {
                if (activityViewModel.previousQuestion())
                    findNavController().navigate(R.id.action_questionFragment_to_initialQuestionsFragment)
            }

        }
    }

    private fun attachObservers() {
        activityViewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> {
                    activityViewModel.setUiStateResting()
                    findNavController().navigate(R.id.action_initialQuestionsFragment_to_questionFragment)
                }

                is Error -> showShortToast(requireContext(), getString(R.string.error_loading_question))
            }
        })

        activityViewModel.nextQuestionLiveData.observe(viewLifecycleOwner, Observer {

            if (it.shouldStop){
                findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
            }else{
                val question = it.question!!

                binding.titleTV.text = question.text

                val explanation =
                    if(!question.explanation.isNullOrEmpty()){
                        question.explanation
                    }else{
                        when(question.type){

                            QUESTION_TYPE_SINGLE, QUESTION_TYPE_GROUP_SINGLE -> getString(R.string.instruction_group_single)

                            QUESTION_TYPE_GROUP_MULTIPLE -> getString(R.string.instruction_group_multiple)

                            else -> getString(R.string.instruction_group_single)
                        }
                    }

                questionItemsAdapter.refill(question.type, question.items)

                binding.explanationTV.text = explanation

            }

        })
    }

}
