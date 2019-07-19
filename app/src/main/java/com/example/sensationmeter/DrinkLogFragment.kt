package com.example.sensationmeter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sensationmeter.database.entity.Drink
import com.example.sensationmeter.database.repository.Repository
import com.example.sensationmeter.database.repository.service.DrinkDao
import com.example.sensationmeter.utility.Data
import com.example.sensationmeter.utility.Log
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_drink_log.*
import javax.inject.Inject

class DrinkLogFragment : Fragment() {
    @Inject lateinit var repository: Repository

    override fun onAttach(context: Context) {
        MainApp.application.appComponent.inject(this)
        super.onAttach(context)
    }

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
        val checkBoxArray: Array<CheckBox> = arrayOf(sugar_checkBox, caffeine_checkBox, alcohol_checkBox, carbonation_checkBox)
        val drinkSpecArray: Array<Int> = arrayOf(0, 0, 0, 0)
        val intakeVolume = drinkVolume_editText.text.toString().toInt()

        for (n in 0 until checkBoxArray.size) {
            if (checkBoxArray[n].isChecked) {
                drinkSpecArray[n] = 1
            }
        }

        repository.logDrink(Single.just(Drink(0, intakeVolume, drinkSpecArray[0], drinkSpecArray[1], drinkSpecArray[2], drinkSpecArray[3])))
        fragmentManager!!.popBackStack()
    }
}
