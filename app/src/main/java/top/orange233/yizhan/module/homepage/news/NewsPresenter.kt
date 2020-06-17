package top.orange233.yizhan.module.homepage.news

import top.orange233.yizhan.common.repository.NewsRepository
import top.orange233.yizhan.data.News

class NewsPresenter(private var view: NewsContract.View) : NewsContract.Presenter,
    NewsAdapter.ItemViewOnClickListener {

    private var newsList: MutableList<News>? = null
    private var newsAdapter: NewsAdapter? = null

    override fun refreshNewsList() {
        NewsRepository.getInstance().getLatestNews()
            .subscribe({
                newsList = it.stories.toMutableList()
                newsAdapter?.notifyDataSetChanged()
            }, {
            })
    }

    override fun getMoreNews() {
        //TODO("Not yet implemented")
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
        //TODO("Not yet implemented")
    }

    override fun getAdapter(): NewsAdapter = newsAdapter!!
}