package it.weMake.covid19Companion.ui.preventionTips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import it.weMake.covid19Companion.databinding.FragmentPreventionTipBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PreventionTipFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreventionTipFragment : Fragment() {

    private var preventionTipTitle: String? = null
    private var preventionTip: String? = null
    private var preventionTipWhy: String? = null

    private var _binding: FragmentPreventionTipBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            preventionTipTitle = it.getString(ARG_PREVENTION_TIP_TITLE)
            preventionTip = it.getString(ARG_PREVENTION_TIP)
            preventionTipWhy = it.getString(ARG_PREVENTION_TIP_WHY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreventionTipBinding.inflate(inflater, container, false)

        // try getting the three arguments passed in herre
        //and set them to thwe textviews of this fragment layout
        // you use view binding to get the reference to the views ibn the fragment

        binding.preventionTipsTitleTV.text = preventionTipTitle
        binding.preventionTipsSummary3TV.text = preventionTipWhy
        binding.preventionTipsSummary1TV.text = preventionTip
//        binding.preventionTipsSummary2TV.text =
//        binding.preventionTipsIV. = R.id


        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_PREVENTION_TIP_TITLE = "preventionTipTitle"
        private const val ARG_PREVENTION_TIP = "preventionTip"
        private const val ARG_PREVENTION_TIP_WHY = "preventionTipWhy"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param preventionTipTitle Parameter 1.
         * @param preventionTip Parameter 2.
         * @return A new instance of fragment PreventionTipFragment.
         */
        @JvmStatic
        fun newInstance(preventionTipTitle: String, preventionTip: String, preventionTipWhy: String) =
            PreventionTipFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PREVENTION_TIP_TITLE, preventionTipTitle)
                    putString(ARG_PREVENTION_TIP, preventionTip)
                    putString(ARG_PREVENTION_TIP_WHY, preventionTipWhy)

                }
            }
    }
}
