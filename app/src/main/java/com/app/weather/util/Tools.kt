package com.app.weather.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Tools {
    private const val INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    private const val OUTPUT_DATE_FORMAT = "EEE, MMM dd yyyy HH:mm"

    fun convertDateToFormt(dateString: String): String {
        var formattedDate = ""
        try {
            if (hasValue(dateString)) {
                var dateFormat = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.US)
                val oldDate = dateFormat.parse(dateString)
                dateFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.US)
                formattedDate = dateFormat.format(oldDate)
            }
        } catch (e: ParseException) {
        }

        return formattedDate
    }

    fun hasValue(value: String?): Boolean {
        try {
            if (value != null && !value.equals("null", ignoreCase = true) && !value.equals("", ignoreCase = true)) {
                return true
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }

    // Hide Soft Keypad
    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}