package it.weMake.covid19Companion.ui.screeningTool.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment

import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentScreeningToolIntroBinding

class ScreeningToolIntroFragment : Fragment() {

    private lateinit var binding: FragmentScreeningToolIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreeningToolIntroBinding.inflate(inflater, container, false)

        binding.startScreeningMB.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_firstQuestionFragment)
        }

        return binding.root
    }

}
