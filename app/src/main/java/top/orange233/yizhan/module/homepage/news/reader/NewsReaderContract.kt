package top.orange233.yizhan.module.homepage.news.reader

import top.orange233.yizhan.data.News
import top.orange233.yizhan.module.base.BasePresenter

class NewsReaderContract {
    interface View {
        fun updateNewsView(news: News)
    }

    interface Presenter : BasePresenter {
        fun getNewsContent(newsId: Int)
    }
}