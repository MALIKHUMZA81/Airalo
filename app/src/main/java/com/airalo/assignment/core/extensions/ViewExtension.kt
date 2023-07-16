package com.airalo.assignment.core.extensions

import android.view.View
import android.widget.Toast
import com.airalo.assignment.App
import java.util.*

/**
 * Extension function to show toast message
 */
fun Any.showToastMsg(message: String) {
    Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * An Extension to make view Visible
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * An Extension to make view Gone
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Returns [Boolean] based on current time.
 * Returns true if hours are between 06:00 pm - 07:00 am
 */
fun checkIsNight(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (currentHour <= 7 || currentHour >= 18)
}
