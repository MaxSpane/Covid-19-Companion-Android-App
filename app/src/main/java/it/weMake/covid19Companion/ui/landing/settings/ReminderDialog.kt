package it.weMake.covid19Companion.ui.landing.settings

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import it.weMake.covid19Companion.R


class ReminderDialog(
    val intervalSet: (intervalInMinutes: Int) -> Unit,
    val onDismissCancel: () -> Unit,
    val username : String
) : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder =
            AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        //val binding = ReminderDialogBinding.bind()
        val view: View = inflater.inflate(R.layout.reminder_dialog, null)

        var hourNP: NumberPicker = view.findViewById(R.id.hour_NP);
        var minuteNP: NumberPicker = view.findViewById(R.id.minute_NP);

        hourNP.maxValue = 24
        hourNP.minValue = 0
        minuteNP.maxValue = 60
        minuteNP.minValue = 0

        builder.setView(view)
            .setTitle("please $username, pick the time Interval")
            .setNegativeButton(
                "cancel"
            ) { _, _ ->
                onDismissCancel()
            }
            .setPositiveButton(
                "ok"
            ) { _, _ ->
                intervalSet(convertToMinutes(hourNP.value, minuteNP.value))
            }

        return builder.create()
    }

    private fun convertToMinutes(hour: Int, minute: Int): Int =
        (hour * 60) + minute

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onDismissCancel()
    }

}
