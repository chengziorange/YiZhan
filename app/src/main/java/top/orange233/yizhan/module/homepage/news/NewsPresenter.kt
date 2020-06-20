package top.orange233.yizhan.module.homepage.news

import android.content.Intent
import top.orange233.yizhan.common.repository.NewsRepository
import top.orange233.yizhan.data.News
import top.orange233.yizhan.module.homepage.news.reader.NewsReaderActivity
import top.orange233.yizhan.util.NewsDateFormatter
import java.util.*

class NewsPresenter(private val view: NewsContract.View) : NewsContract.Presenter,
    NewsAdapter.ItemViewOnClickListener {

    private var newsList: MutableList<News>? = null
    private var newsAdapter: NewsAdapter? = null
    private var dateToLoad: Calendar = Calendar.getInstance()

    override fun refreshNewsList() {
        NewsRepository.getInstance().getLatestNews()
            .subscribe({
                newsList?.clear()
                newsList?.addAll(it.stories)
                view.updateNewsList()
                view.finishRefresh()
                dateToLoad = Calendar.getInstance()
            }, {
            })
    }

    override fun loadMoreNews() {
        if (dateToLoad < NewsDateFormatter.getOldestNewsDate()) {
            return
        }
        NewsRepository.getInstance().getBeforeNews(NewsDateFormatter.format(dateToLoad.time))
            .subscribe({
                newsList?.addAll(it.stories.toMutableList())
                view.updateNewsList()
                view.finishLoadMore()
                dateToLoad.set(Calendar.DATE, -1)
            }, {})
    }

    override fun start() {
        if (newsList == null) {
            newsList = mutableListOf()
        }
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(newsList, this)
        }
        view.fetchOnFirstOpen()
    }

    override fun onClickNews(news: News) {
        val intent = Intent(view.getViewContext(), NewsReaderActivity::class.java)
        intent.putExtra(NewsReaderActivity.NEWS_ID, news.newsId)
        view.getViewContext().startActivity(intent)
    }

    override fun getAdapter(): NewsAdapter = newsAdapter!!
}