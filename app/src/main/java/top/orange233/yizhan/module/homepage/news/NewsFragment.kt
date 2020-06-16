package top.orange233.yizhan.module.homepage.news

import com.orhanobut.logger.Logger
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class NewsFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_news

    override fun initView() {
        Logger.d("Created NewsFragment")
    }

    override fun initEvent() {
    }
}