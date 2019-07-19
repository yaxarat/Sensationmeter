package com.example.sensationmeter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.sensationmeter.utility.Log
import com.example.sensationmeter.utility.Permission
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermissions()
        Log.makeSaveDirectory(this.requireContext())

        drink_button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.to_drinkLogFragment))
        void_button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.to_voidLogFragment))
        meter_button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.to_meterFragment))
        settings_fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.to_settingsFragment))
    }

    private fun checkPermissions() {
        val permissions = arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        for (permission in permissions) {
            if (!Permission.checkPermissions(context, permission)) {
                requestPermissions(permissions, 1)
            }
        }
    }
}
