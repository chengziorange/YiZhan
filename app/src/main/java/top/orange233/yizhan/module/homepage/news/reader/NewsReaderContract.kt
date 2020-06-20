package top.orange233.yizhan.module.homepage.news.reader

import top.orange233.yizhan.module.base.BasePresenter

class NewsReaderContract {
    interface View {
        fun finishRefreshPage()
        fun finishLoadMore()
    }

    interface Presenter : BasePresenter {
        fun getAdapter(): NewsCommentAdapter
        fun refreshPage()
        fun loadMoreComment()
    }
}