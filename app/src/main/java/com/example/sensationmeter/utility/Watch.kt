package com.example.sensationmeter.utility

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@SuppressLint("NewApi") //API26 required for .now()
class Watch {
    companion object {
        private var formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN)
        private var currentSystemTime: String = LocalDateTime.now().format(formatter)

        fun currentTime(): String {
            return currentSystemTime
        }
    }
}