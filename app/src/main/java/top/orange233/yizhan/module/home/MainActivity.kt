package top.orange233.yizhan.module.home

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gyf.immersionbar.ImmersionBar
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getLayout() = R.layout.activity_main

    override fun initView() {
        ImmersionBar.with(this)
            .transparentStatusBar()
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .init()

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.nav_fragment)
        navView.setupWithNavController(navController)
    }

    override fun initEvent() {
    }
}