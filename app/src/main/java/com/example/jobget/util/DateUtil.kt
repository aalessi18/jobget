package com.example.jobget.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

fun retrieveDate(stringPattern: String, date: String? = ""): String =
    if (date.isNullOrEmpty()) {
        SimpleDateFormat(stringPattern, Locale.getDefault()).format(
            Date()
        )
    } else {
        date
    }