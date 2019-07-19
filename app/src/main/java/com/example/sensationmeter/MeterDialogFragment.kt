package com.example.sensationmeter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sensationmeter.database.entity.Survey
import com.example.sensationmeter.database.repository.Repository
import com.example.sensationmeter.utility.Data
import com.example.sensationmeter.utility.Log
import io.reactivex.Single
import javax.inject.Inject

class MeterDialogFragment : DialogFragment() {
    @Inject
    lateinit var repository: Repository

    override fun onAttach(context: Context) {
        MainApp.application.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>() // Where we track the selected items
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            // Set the dialog title
            builder.setTitle(R.string.meter_dialog_title)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(
                    R.array.senses, null
                ) { _, which, isChecked ->
                    if (isChecked) {
                        // If the user checked the item, add it to the selected items
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        // Else, if the item is already in the array, remove it
                        selectedItems.remove(Integer.valueOf(which))
                    }
                }
                .setPositiveButton(R.string.app_yes) { _, _ ->
                    if (selectedItems.size > 0) {
                        setData(selectedItems)
                    }
                }
                .setNegativeButton(R.string.app_no) { _, _ ->
                    // User cancelled the dialog
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setData(indexList: ArrayList<Int>) {
        val dataValue = intArrayOf(0, 0, 0, 0, 0)
        for (index in indexList) {
            dataValue[index] = 1
        }
        repository.logSurvey(Single.just(Survey(0, dataValue[0], dataValue[1], dataValue[2], dataValue[3], dataValue[4])))
    }
}