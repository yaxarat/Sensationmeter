package com.example.sensationmeter.utility

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class FragmentTransaction {
    companion object {
        fun beginTransactionWithBackStack(activity: FragmentActivity?, fromFragment: Int, toFragment: Fragment) {
            activity?.supportFragmentManager?.beginTransaction()?.replace(fromFragment, toFragment)?.addToBackStack(null)?.commit()
        }
        fun beginTransaction(activity: FragmentActivity?, fromFragment: Int, toFragment: Fragment) {
            activity?.supportFragmentManager?.beginTransaction()?.replace(fromFragment, toFragment)?.commit()
        }
    }
}