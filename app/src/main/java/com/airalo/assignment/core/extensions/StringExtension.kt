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

private fun standardDateFormat(): SimpleDateFormat {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
}

/**
 * An Extension to get TodayDate.
 * @return formatted date as yyyy-MM-dd'T'HH:mm.
 */
fun getCurrentDateAndTime(): String {
    return standardDateFormat().format(Calendar.getInstance().time)
}

fun String.getFormattedStructuredDate(): Date? {
    return standardDateFormat().parse(this) ?: return null
}

fun String?.getTimeInMillis(): Long {
    val date = this?.getFormattedStructuredDate() ?: return 0L
    return date.time
}

fun String.getTotalDays(): Long {
    val today = getCurrentDateAndTime().getTimeInMillis()
    val diff = today - getTimeInMillis()
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
}

/**
 * An Extension to getDateInLocalZone.
 * @param string date as yyyy-MM-dd'T'HH:mm.
 * @return local date in same format
 */
fun String.getDateInLocalZone(): String? {
    val df = standardDateFormat()
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(this) ?: return null
    df.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
    return df.format(date)
}

@SuppressLint("SimpleDateFormat")
fun String.getDateInUIFormat(): String? {
    val date = getFormattedStructuredDate() ?: return null
    return SimpleDateFormat("yyyy-MM-dd' at 'HH:mm").format(date)
}

