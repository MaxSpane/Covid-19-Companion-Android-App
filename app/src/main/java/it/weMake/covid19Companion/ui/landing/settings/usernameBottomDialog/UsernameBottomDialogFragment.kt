package it.weMake.covid19Companion.ui.landing.settings.usernameBottomDialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentUsernameBinding
import it.weMake.covid19Companion.databinding.FragmentWashHandReminderLocationBinding
import it.weMake.covid19Companion.models.washHandsReminderLocations.WashHandsReminderLocation
import it.weMake.covid19Companion.utils.showLongToast

/**
 * A simple [Fragment] subclass.
 */
class UsernameBottomDialogFragment(
    private var username : String,
    private val setUsername: (username: String?) -> Unit
                                    )
    : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var binding: FragmentUsernameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsernameBinding.inflate(inflater, container, false)
        binding.usernameET.setText(username)


        binding.saveMB.setOnClickListener(this)
        return binding.root
    }



    override fun onClick(v: View) {
        when(v.id){

            R.id.saveMB -> {

                if (binding.usernameET.text.toString().isEmpty()){
                    binding.usernameET.setText("Survivor")
                }
                else {
                    val thisUsername =  binding.usernameET.text.toString().trim()
                    setUsername(thisUsername)
                }

                dismiss()
            }

        }
    }



}
