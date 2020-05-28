package it.weMake.covid19Companion.ui.landing.settings.washHandsReminderLocation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentWashHandReminderLocationBinding
import it.weMake.covid19Companion.models.washHandsReminderLocations.WashHandsReminderLocation
import it.weMake.covid19Companion.utils.showLongToast

/**
 * A simple [Fragment] subclass.
 */
class WashHandsReminderLocationBottomDialogFragment(
    private val isNewLocation: Boolean,
    private val reminderLocation: WashHandsReminderLocation,
    private val insertWashHandsReminderLocation: (location: WashHandsReminderLocation) -> Unit,
    private val updateWashHandsReminderLocation: (location: WashHandsReminderLocation, addressChanged: Boolean) -> Unit
) : BottomSheetDialogFragment(), View.OnClickListener {

    private val AUTOCOMPLETE_REQUEST_CODE:Int = 1
    private val autocompleteFields: List<Place.Field> =
        listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG)
    private var isAddressUpdated = false

    private lateinit var binding: FragmentWashHandReminderLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWashHandReminderLocationBinding.inflate(inflater, container, false)

        binding.locationNameET.setText(reminderLocation.name)
        if (reminderLocation.address.isNotEmpty())
            binding.locationAddressValueTV.text = reminderLocation.address

        binding.locationAddressValueTV.setOnClickListener(this)
        binding.saveMB.setOnClickListener(this)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                reminderLocation.address = place.address!!

                val latLng = place.latLng!!
                reminderLocation.lat = latLng.latitude
                reminderLocation.lng = latLng.longitude

                binding.locationAddressValueTV.text = reminderLocation.address
                isAddressUpdated = true
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                val status = Autocomplete.getStatusFromIntent(data!!)
//                Log.i(TAG, status.getStatusMessage())
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.locationAddressValueTV -> startAutocompleteIntent()

            R.id.saveMB -> {
                val locationName = binding.locationNameET.text.toString().trim()

                if (locationName.isEmpty()){
                    showLongToast(requireContext(), "Enter a Name for the Location")
                }else if(reminderLocation.address.isEmpty()){
                    showLongToast(requireContext(), "Enter an Address for the Location")
                }else{
                    reminderLocation.name = locationName
                    if (isNewLocation){
                        insertWashHandsReminderLocation(reminderLocation)
                    }else{
                        updateWashHandsReminderLocation(reminderLocation, isAddressUpdated)
                    }
                    dismiss()
                }

            }

        }
    }

    // Start the autocomplete intent.
    private fun startAutocompleteIntent(){
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY, autocompleteFields
        ).build(requireContext())
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }

}
