package top.orange233.yizhan.util

import android.content.Context
import top.orange233.yizhan.app.YiZhanApp
import java.lang.IllegalArgumentException

class Preference private constructor() {
    companion object {
        const val KEY_COOKIE_JSESSIONID = "JSESSIONID"
        const val KEY_IS_LOGGED_IN = "IS_LOGGED_IN"
        const val KEY_PROFILE_CHANGED = "PROFILE_CHANGED"

        private val sp = YiZhanApp.context.getSharedPreferences(
            YiZhanApp.context.packageName,
            Context.MODE_PRIVATE
        )!!

        val instance: Preference by lazy {
            Preference()
        }
    }

    fun <T> getValue(key: String, defaultValue: T): T {
        return with(sp) {
            when (defaultValue) {
                is Int -> getInt(key, defaultValue)
                is Long -> getLong(key, defaultValue)
                is Boolean -> getBoolean(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                is String -> getString(key, defaultValue)
                else -> throw IllegalArgumentException("SharedPreference cannot get this type of value")
            } as T
        }
    }

    fun <T> putValue(key: String, value: T) {
        with(sp.edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("SharedPreference cannot put this type of value")
            }
        }.apply()
    }

    fun containsKey(key: String): Boolean {
        return sp.contains(key)
    }
}