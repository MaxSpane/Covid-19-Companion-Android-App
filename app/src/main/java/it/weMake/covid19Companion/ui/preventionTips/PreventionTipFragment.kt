package it.weMake.covid19Companion.ui.preventionTips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import it.weMake.covid19Companion.databinding.FragmentPreventionTipBinding
import it.weMake.covid19Companion.models.preventionTips.PreventionTip
import it.weMake.covid19Companion.utils.getDrawableResourceId

/**
 * A simple [Fragment] subclass.
 * Use the [PreventionTipFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreventionTipFragment : Fragment() {

    private lateinit var preventionTip: PreventionTip

    private var _binding: FragmentPreventionTipBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            preventionTip = it.getParcelable(ARG_PREVENTION_TIP)!!
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

        binding.preventionTipsTitleTV.text = preventionTip.title
        binding.preventionTipsSummary3TV.text = preventionTip.preventionTipWhy
        binding.preventionTipsSummary1TV.text = preventionTip.preventionTip

        getDrawableResourceId(requireContext(), preventionTip.iconId)?.let {
            binding.preventionTipsIV.setImageResource(it)
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_PREVENTION_TIP = "preventionTip"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param preventionTipTitle Parameter 1.
         * @param preventionTip Parameter 2.
         * @return A new instance of fragment PreventionTipFragment.
         */
        @JvmStatic
        fun newInstance(preventionTip: PreventionTip) =
            PreventionTipFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PREVENTION_TIP, preventionTip)
                }
            }
    }
}
