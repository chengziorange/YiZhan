package top.orange233.yizhan.module.profile

import com.gyf.immersionbar.ktx.immersionBar
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class ProfileFragment:BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_profile

    override fun initView() {
        immersionBar {
            statusBarColor(R.color.colorPrimary)
            statusBarDarkFont(false )
        }
    }

    override fun initEvent() {
    }
}