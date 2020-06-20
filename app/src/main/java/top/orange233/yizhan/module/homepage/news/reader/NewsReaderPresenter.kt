package top.orange233.yizhan.module.homepage.news.reader

import top.orange233.yizhan.common.repository.NewsRepository

class NewsReaderPresenter(private val view: NewsReaderContract.View) :
    NewsReaderContract.Presenter {

    override fun getNewsContent(newsId: Int) {
        NewsRepository.getInstance().getNewsById(newsId)
            .subscribe({
                view.updateNewsView(it)
            }, {})
    }

    override fun start() {
    }
}