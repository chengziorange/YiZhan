package top.orange233.yizhan.module.homepage

import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_homepage.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class HomepageFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_homepage

    override fun initView() {
        view_pager.adapter = HomepageAdapter(this)
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = when (position) {
                0 -> "新闻"
                1 -> "漫画"
                2 -> "番剧"
                else -> throw Throwable("Unknown homepage category in tabs.")
            }
        }.attach()
    }

    override fun initEvent() {
    }
}