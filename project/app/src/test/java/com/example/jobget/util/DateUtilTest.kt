package com.example.jobget.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilTest {

    companion object {
        const val PATTERN = "dd MMM, yyyy"
    }

    @Test
    fun testIsDateFieldEmpty() {
        var date: String? = null
        val dateObject = Date()

        assertEquals(
            SimpleDateFormat(PATTERN, Locale.getDefault()).format(dateObject),
            retrieveDate(PATTERN, date)
        )

        date = ""
        assertEquals(
            SimpleDateFormat(PATTERN, Locale.getDefault()).format(dateObject),
            retrieveDate(PATTERN, date)
        )

        date = "03 Jun, 2022"
        assertEquals(date, retrieveDate("dd MMM, yyyy", date))
    }
}