package top.orange233.yizhan.app

import android.app.Application
import android.content.Context

class YiZhanApp : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}