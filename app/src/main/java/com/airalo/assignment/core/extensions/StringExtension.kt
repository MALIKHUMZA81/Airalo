package com.airalo.assignment.core.extensions

import android.annotation.SuppressLint
import com.airalo.assignment.App
import com.airalo.assignment.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

/**
 * The StringExtension.kt
 */

/**
 * Extension function to noNetworkErrorMessage
 */
fun noNetworkErrorMessage() =
    App.getAppContext().getString(R.string.message_no_network_connected_str)

/**
 * Extension function to somethingWentWrong
 */
fun somethingWentWrong() = App.getAppContext().getString(R.string.message_something_went_wrong_str)


/**
 * An Extension to get TodayDate.
 * @return formatted date as yyyy-MM-dd'T'HH:mm.
 */




/**
 * An Extension to getDateInLocalZone.
 * @param string date as yyyy-MM-dd'T'HH:mm.
 * @return local date in same format
 */


