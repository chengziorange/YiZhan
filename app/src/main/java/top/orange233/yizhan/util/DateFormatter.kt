package top.orange233.yizhan.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private val newsFormatter = SimpleDateFormat("yyyyMMdd", Locale.CHINA)
    private val commentFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)

    fun formatNewsDate(date: Date) = newsFormatter.format(date)

    fun getOldestNewsDate(): Calendar {
        val cal: Calendar = Calendar.getInstance()
        cal.set(2013, 4, 20)
        return cal
    }

    fun formatCommentDate(unixTimestamp: Long) = commentFormatter.format(Date(unixTimestamp))
}