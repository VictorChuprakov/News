package com.example.movies.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDate(dateString: String): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = ZonedDateTime.parse(dateString, formatter)
    val customFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH)
    return dateTime.format(customFormatter)
}
