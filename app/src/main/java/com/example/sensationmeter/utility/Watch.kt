package com.example.sensationmeter.utility

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@SuppressLint("NewApi")
class Watch {
    companion object {
        private var formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN)
        private var currentSystemTime: String = LocalDateTime.now().toString()

        fun currentTime(): String {
            return currentSystemTime
        }

        fun toDate(dateString: String): LocalDateTime {
            return LocalDateTime.parse(dateString)
        }

        fun format(time: LocalDateTime): String {
            return time.format(formatter)
        }
    }
}