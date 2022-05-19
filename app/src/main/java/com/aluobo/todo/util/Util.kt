package com.aluobo.todo.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * 隐藏软件盘
 */
fun hideKeyboard(activity: Activity) {

    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    activity.currentFocus?.let {
        inputMethodManager.hideSoftInputFromWindow(
            it.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

}