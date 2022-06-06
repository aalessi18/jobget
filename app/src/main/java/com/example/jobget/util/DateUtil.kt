package com.example.jobget.util

import android.app.Activity
import com.example.jobget.R
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

fun isDateFieldEmpty(date: String?, activity: Activity) =
    if (date.isNullOrEmpty()) {
        SimpleDateFormat(activity.getString(R.string.date_patten), Locale.getDefault()).format(
            Date()
        )
    } else {
        date
    }