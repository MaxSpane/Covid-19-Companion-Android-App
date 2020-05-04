package it.weMake.covid19Companion.ui.screeningTool.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentInitialQuestionsBinding

/**
 * A simple [Fragment] subclass.
 */
class InitialQuestionsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentInitialQuestionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInitialQuestionsBinding.inflate(inflater, container, false)

        binding.nextMB.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.nextMB -> findNavController().navigate(R.id.action_initialQuestionsFragment_to_questionFragment)

        }
    }

}
