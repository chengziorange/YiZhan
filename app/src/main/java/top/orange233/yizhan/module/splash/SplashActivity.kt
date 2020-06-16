package top.orange233.yizhan.module.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.gyf.immersionbar.ktx.immersionBar
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import top.orange233.yizhan.R
import top.orange233.yizhan.module.home.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        immersionBar {
            fitsSystemWindows(true)
        }

        Logger.addLogAdapter(AndroidLogAdapter())

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },1000)
    }
}