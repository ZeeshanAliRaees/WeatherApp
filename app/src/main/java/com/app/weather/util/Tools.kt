package com.example.weather.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.ParseException
import java.text.SimpleDateFormat

object Tools {
    const val INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val OUTPUT_DATE_FORMAT = "EEE, MMM dd yyyy"

    fun convertDateToFormt(dateString: String?): String {
        var formattedDate = ""
        try {
            if (Tools.hasValue(dateString)) {
                var dateFormat = SimpleDateFormat(INPUT_DATE_FORMAT);
                var oldDate = dateFormat.parse(dateString)
                dateFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT)
                formattedDate = dateFormat?.format(oldDate)
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

    // Check Internet Connection
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    // Hide Soft Keypad
    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}