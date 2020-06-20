package top.orange233.yizhan.module.homepage.news

import android.content.Context
import top.orange233.yizhan.module.base.BasePresenter

interface NewsContract {
    interface View {
        fun getViewContext(): Context
        fun updateNewsList()
        fun fetchOnFirstOpen()
        fun finishRefresh()
        fun finishLoadMore()
    }

    interface Presenter : BasePresenter {
        fun getAdapter(): NewsAdapter
        fun refreshNewsList()
        fun loadMoreNews()
    }
}