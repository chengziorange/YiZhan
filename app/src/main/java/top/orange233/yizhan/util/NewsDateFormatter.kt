package top.orange233.yizhan.util

import java.text.SimpleDateFormat
import java.util.*

object NewsDateFormatter {
    private val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.CHINA)

    fun format(date: Date) = simpleDateFormat.format(date)

    fun getOldestNewsDate(): Calendar {
        val cal: Calendar = Calendar.getInstance()
        cal.set(2013, 4, 20)
        return cal
    }
}