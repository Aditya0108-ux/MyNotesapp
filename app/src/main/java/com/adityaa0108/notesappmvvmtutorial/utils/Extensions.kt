package com.adityaa0108.notesappmvvmtutorial.utils

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
  object  Extensions {

    fun hideKeyboard(fragment: Fragment) {
        val view = fragment.view
        if (view != null) {
            val imm = fragment.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, HIDE_NOT_ALWAYS)
        }
    }



}