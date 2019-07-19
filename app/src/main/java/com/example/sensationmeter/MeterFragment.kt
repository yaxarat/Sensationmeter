package com.example.sensationmeter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.sensationmeter.utility.Data
import com.example.sensationmeter.utility.Log
import com.example.sensationmeter.setting.ApplicationSetting
import kotlinx.android.synthetic.main.fragment_meter.*

class MeterFragment : Fragment() {
    private lateinit var thisContext: Context
    private var changed: Boolean = false
    private var lastMeterValue: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Create context for this fragment
        thisContext = container!!.context
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the initial state
        initialize()

        // On seek bar change
        progress_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            // Set the meter text to the current value of seekBar
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progress_textView.text = ("$progress%")
                waveLoadingView.progressValue = progress
            }

            // Saves the original meter value in case of undo
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                progress_textView.text = progress_seekBar.progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                changed = true
                toggleButtons()
            }
        })
        // On accept button press
        submit_button.setOnClickListener {
            lastMeterValue = progress_seekBar.progress
            ApplicationSetting(thisContext).setSensationVal(lastMeterValue)
            Log(context!!).makeEntry(Data(lastMeterValue))
            showMeterDialog()
            changed = false
            toggleButtons()
            fragmentManager!!.popBackStack()
        }
        // On undo button press
        undo_button.setOnClickListener {
            changed = false
            progress_seekBar.progress = lastMeterValue
            toggleButtons()
        }
    }

    private fun initialize() {
        lastMeterValue = ApplicationSetting(thisContext).getSensationVal()
        progress_seekBar.progress = lastMeterValue
        progress_textView.text = ("$lastMeterValue%")
        waveLoadingView.progressValue = lastMeterValue
        toggleButtons()
    }

    private fun toggleButtons() {
        if (changed) {
            submit_button.visibility = View.VISIBLE
            undo_button.visibility = View.VISIBLE
        } else {
            submit_button.visibility = View.INVISIBLE
            undo_button.visibility = View.INVISIBLE
        }
    }

    private fun showMeterDialog() {
        val dialog = MeterDialogFragment()
        dialog.show(fragmentManager!!, "MeterDialogFragment")
    }
}
