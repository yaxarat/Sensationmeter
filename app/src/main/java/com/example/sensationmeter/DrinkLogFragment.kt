package com.example.sensationmeter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sensationmeter.utility.Data
import com.example.sensationmeter.utility.Log
import kotlinx.android.synthetic.main.fragment_drink_log.*

class DrinkLogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drink_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_button.setOnClickListener {checkInput()}
    }

    private fun checkInput() {
        if (drinkVolume_editText.text!!.isNotBlank()) {
            submit()
        } else {
            Toast.makeText(context, R.string.drink_log_error_toast2, Toast.LENGTH_LONG).show()
        }
    }

    private fun submit() {
        val intakeVolume = drinkVolume_editText.text.toString().toInt()
        var sugar = 0
        var caffeine = 0
        var alcohol = 0
        var carbonation = 0

        if (sugar_checkBox.isChecked) {
            sugar = 1
        }
        if (caffeine_checkBox.isChecked) {
            caffeine = 1
        }
        if (alcohol_checkBox.isChecked) {
            alcohol = 1
        }
        if (carbonation_checkBox.isChecked) {
            carbonation = 1
        }

        Log(context!!).makeEntry(Data(intakeVolume, sugar, caffeine, alcohol, carbonation, "DrinkLogFragment"))
        fragmentManager!!.popBackStack()
    }
}
