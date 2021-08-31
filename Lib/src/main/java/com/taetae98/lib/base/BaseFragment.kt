package com.taetae98.lib.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected fun setSupportActionBar(toolbar: Toolbar) {
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(toolbar)
        }
    }

    protected fun finish() {
        requireActivity().finish()
    }
}