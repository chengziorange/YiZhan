package top.orange233.yizhan.common.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import top.orange233.yizhan.common.network.NewsService

class NewsRepository private constructor(private val newsService: NewsService) {

    companion object {
        private val INSTANCE: NewsRepository = NewsRepository(NewsService.create())
        fun getInstance() = INSTANCE
    }

    fun getLatestNews() =
        newsService.getLatestNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getBeforeNews(date: String) =
        newsService.getBeforeNews(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNewsById(newsId: Int) =
        newsService.getNewsById(newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}