package top.orange233.yizhan.module.homepage.news

import android.content.Context
import top.orange233.yizhan.module.base.BasePresenter

interface NewsContract {
    interface View {
        fun getViewContext(): Context
        fun updateNewsList()
        fun fetchOnFirstOpen()
    }

    interface Presenter : BasePresenter {
        fun getAdapter(): NewsAdapter
        fun refreshNewsList(): Boolean
        fun loadMoreNews(): Boolean
    }
}