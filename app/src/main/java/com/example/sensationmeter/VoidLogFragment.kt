package com.example.sensationmeter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import com.example.sensationmeter.database.entity.Void
import com.example.sensationmeter.database.repository.Repository
import com.example.sensationmeter.utility.Watch
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_void_log.*
import javax.inject.Inject

class VoidLogFragment : Fragment() {
    @Inject
    lateinit var repository: Repository

    private var locationLabel = ""
    private var activeColor = "#FFFF4081"
    private var inactiveColor = "#FF000000"

    override fun onAttach(context: Context) {
        MainApp.application.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_void_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleTextColor()

        // Button navigation to other activities
        save_button.setOnClickListener {
            checkInput()
        }

        // Location buttons
        home_button.setOnClickListener {
            locationLabel = "Home"
            toggleTextColor()
            Data().locationLabel = locationLabel
        }
        work_button.setOnClickListener {
            locationLabel = "Work"
            toggleTextColor()
            Data().locationLabel = locationLabel
        }
        school_button.setOnClickListener {
            locationLabel = "School"
            toggleTextColor()
            Data().locationLabel = locationLabel
        }
        other_button.setOnClickListener {
            locationLabel = "Other"
            toggleTextColor()
            Data().locationLabel = locationLabel
        }
    }

    private fun toggleTextColor() {
        when (locationLabel) {
            "" -> {
                home_button.setTextColor(inactiveColor.toColorInt())
                work_button.setTextColor(inactiveColor.toColorInt())
                school_button.setTextColor(inactiveColor.toColorInt())
                other_button.setTextColor(inactiveColor.toColorInt())
            }
            "Home" -> {
                home_button.setTextColor(activeColor.toColorInt())
                work_button.setTextColor(inactiveColor.toColorInt())
                school_button.setTextColor(inactiveColor.toColorInt())
                other_button.setTextColor(inactiveColor.toColorInt())
            }
            "Work" -> {
                home_button.setTextColor(inactiveColor.toColorInt())
                work_button.setTextColor(activeColor.toColorInt())
                school_button.setTextColor(inactiveColor.toColorInt())
                other_button.setTextColor(inactiveColor.toColorInt())
            }
            "School" -> {
                home_button.setTextColor(inactiveColor.toColorInt())
                work_button.setTextColor(inactiveColor.toColorInt())
                school_button.setTextColor(activeColor.toColorInt())
                other_button.setTextColor(inactiveColor.toColorInt())
            }
            "Other" -> {
                home_button.setTextColor(inactiveColor.toColorInt())
                work_button.setTextColor(inactiveColor.toColorInt())
                school_button.setTextColor(inactiveColor.toColorInt())
                other_button.setTextColor(activeColor.toColorInt())
            }
        }
    }

    private fun checkInput() {
        if (void_editText.text!!.isNotBlank() and locationLabel.isNotBlank()) {
            submit()
        } else {
            Toast.makeText(
                this.context,
                "Please fill in all the fields and mark your current location.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun submit() {
        val voidVolume = void_editText.text.toString().toInt()
        repository.logVoid(Single.just(Void(0, Watch.currentTime(), voidVolume, locationLabel)))
        fragmentManager!!.popBackStack()
    }
}
