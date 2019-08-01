package com.example.sensationmeter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sensationmeter.setting.ApplicationSetting
import com.example.sensationmeter.setting.UserInformation
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsLoginFragment : Fragment() {
    private var unlocked: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        check_button.setOnClickListener { findNavController().navigate(R.id.toSettingFragment) }

//        toggle()

//        check_button.setOnClickListener {
//            if (!unlocked) {
//                checkPass()
//                toggle()
//            } else {
//                setUserInformation()
//                toggle()
//                fragmentManager!!.popBackStack()
//            }
//        }
    }

    private fun setUserInformation() {
        if (!uid_editText.text.isNullOrBlank() && !password_editText.text.isNullOrBlank()) {
            ApplicationSetting(context!!).setSensationVal(0)
            UserInformation(context!!).setName(uid_editText.text.toString())
            ApplicationSetting(context!!).setPassword(password_editText.text.toString())
            Toast.makeText(context, R.string.settings_warning_both, Toast.LENGTH_LONG).show()
            unlocked = false
        } else if (uid_editText.text.isNullOrBlank() && password_editText.text.isNullOrBlank()) {
            Toast.makeText(context, R.string.settings_warning_neither, Toast.LENGTH_LONG).show()
            unlocked = false
        } else if (uid_editText.text.isNullOrBlank()) {
            ApplicationSetting(context!!).setPassword(password_editText.text.toString())
            Toast.makeText(context, R.string.settings_warning_uid, Toast.LENGTH_LONG).show()
            unlocked = false
        } else if (password_editText.text.isNullOrBlank()) {
            ApplicationSetting(context!!).setSensationVal(0)
            UserInformation(context!!).setName(uid_editText.text.toString())
            Toast.makeText(context, R.string.settings_warning_pass, Toast.LENGTH_LONG).show()
            unlocked = false
        }
    }

    private fun checkPass() {
        if (
            !passwordCheck_editText.text.isNullOrBlank() &&
            (passwordCheck_editText.text.toString() == AESEncryption.decrypt(ApplicationSetting(context!!).getMasterPassword()) ||
            passwordCheck_editText.text.toString() == AESEncryption.decrypt(ApplicationSetting(context!!).getPassword()))
        ) {
            unlocked = true
        } else {
            Toast.makeText(context, R.string.settings_wrong_password, Toast.LENGTH_LONG).show()
        }
    }

    private fun toggle() {
        if (!unlocked) {
            hint_textView.visibility = View.INVISIBLE
            pass_textInputLayout.visibility = View.INVISIBLE
            uid_textInputLayout.visibility = View.INVISIBLE
            check_textInputLayout.visibility = View.VISIBLE
            check_button.setText(R.string.app_submit)
        } else {
            hint_textView.visibility = View.VISIBLE
            pass_textInputLayout.visibility = View.VISIBLE
            uid_textInputLayout.visibility = View.VISIBLE
            check_textInputLayout.visibility = View.INVISIBLE
            check_button.setText(R.string.app_save)
        }
    }
}
