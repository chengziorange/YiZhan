package top.orange233.yizhan.module.homepage.news

import top.orange233.yizhan.module.base.BasePresenter

interface NewsContract {
    interface View {
        fun updateNewsList()
    }

    interface Presenter : BasePresenter {
        fun getAdapter(): NewsAdapter
        fun refreshNewsList()
        fun getMoreNews()
    }
}