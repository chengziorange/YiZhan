package top.orange233.yizhan.module.friend

import com.gyf.immersionbar.ktx.immersionBar
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class FriendFragment:BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_friend

    override fun initView() {
        immersionBar {
            transparentStatusBar()
            statusBarDarkFont(true)
            fitsSystemWindows(true)
        }
    }

    override fun initEvent() {
    }
}