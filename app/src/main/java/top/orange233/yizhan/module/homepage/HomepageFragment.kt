package top.orange233.yizhan.module.homepage

import kotlinx.android.synthetic.main.fragment_homepage.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class HomepageFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_homepage

    override fun initView() {
        view_pager.adapter = HomepageAdapter(childFragmentManager)
        view_pager.offscreenPageLimit = 2
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun initEvent() {
    }
}