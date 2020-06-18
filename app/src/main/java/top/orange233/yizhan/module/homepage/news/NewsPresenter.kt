package top.orange233.yizhan.module.homepage.news

import top.orange233.yizhan.common.repository.NewsRepository
import top.orange233.yizhan.data.News
import top.orange233.yizhan.util.NewsDateFormatter
import java.util.*

class NewsPresenter(private var view: NewsContract.View) : NewsContract.Presenter,
    NewsAdapter.ItemViewOnClickListener {

    private var newsList: MutableList<News>? = null
    private var newsAdapter: NewsAdapter? = null
    private var dateToLoad: Calendar = Calendar.getInstance()

    override fun refreshNewsList(): Boolean {
        var isSuccess = false
        NewsRepository.getInstance().getLatestNews()
            .subscribe({
                newsList?.clear()
                newsList?.addAll(it.stories)
                view.updateNewsList()
                dateToLoad = Calendar.getInstance()
                isSuccess = true
            }, {
                isSuccess = false
            })
        return isSuccess
    }

    override fun loadMoreNews(): Boolean {
        var isSuccess = false
        if (dateToLoad < NewsDateFormatter.getOldestNewsDate()) {
            return false
        }
        NewsRepository.getInstance().getBeforeNews(NewsDateFormatter.format(dateToLoad.time))
            .subscribe({
                newsList?.addAll(it.stories.toMutableList())
                view.updateNewsList()
                isSuccess = true
                dateToLoad.set(Calendar.DATE, -1)
            }, {
                isSuccess = false
            })
        return isSuccess
    }

    override fun start() {
        if (newsList == null) {
            newsList = mutableListOf()
        }
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(newsList, this)
        }
        refreshNewsList()
    }

    override fun onClickNews(news: News) {
        //TODO
    }

    override fun getAdapter(): NewsAdapter = newsAdapter!!
}