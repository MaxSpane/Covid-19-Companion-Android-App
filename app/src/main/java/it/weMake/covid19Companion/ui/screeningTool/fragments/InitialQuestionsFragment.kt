package it.weMake.covid19Companion.ui.screeningTool.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import it.weMake.covid19Companion.R

/**
 * A simple [Fragment] subclass.
 */
class InitialQuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial_questions, container, false)
    }

}
