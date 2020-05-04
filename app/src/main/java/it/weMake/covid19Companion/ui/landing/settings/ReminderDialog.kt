package it.weMake.covid19Companion.ui.landing.settings

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.ReminderDialogBinding


class ReminderDialog(
    val intervalSet: (intervalInMinutes: Int, intervalInMinutes2: Int) -> Unit
) : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder =
            AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        //val binding = ReminderDialogBinding.bind()
        val view: View = inflater.inflate(R.layout.reminder_dialog, null)

        var picker1: NumberPicker? = view.findViewById(R.id.numberpicker_main_picker);
        var picker2: NumberPicker? = view.findViewById(R.id.numberpicker_main_picker2);
        if (picker1 != null) {
            picker1.maxValue = 23
        }
        if (picker1 != null) {
            picker1.minValue = 0
        }
        if (picker2 != null) {
            picker2.maxValue = 59
        }
        if (picker2 != null) {
            picker2.minValue = 0
        }

        builder.setView(view)
            .setTitle("please survivor, pick the time Interval")
            .setNegativeButton(
                "cancel"
            ) { _, _ -> }
            .setPositiveButton(
                "ok"
            ) { _, _ ->
                intervalSet(picker1!!.value, picker2!!.value)
            }
        return builder.create();
    }

    }
