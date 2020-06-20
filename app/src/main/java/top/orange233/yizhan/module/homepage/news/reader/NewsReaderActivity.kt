package top.orange233.yizhan.module.homepage.news.reader

import com.bumptech.glide.Glide
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_news_reader.*
import top.orange233.yizhan.R
import top.orange233.yizhan.data.News
import top.orange233.yizhan.module.base.BaseActivity

class NewsReaderActivity : BaseActivity(), NewsReaderContract.View {
    companion object {
        const val NEWS_ID = "news_id"
    }

    private lateinit var presenter: NewsReaderPresenter

    private var news: News? = null

    override fun getLayout(): Int = R.layout.activity_news_reader

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            transparentStatusBar()
            statusBarDarkFont(true)
        }

        presenter = NewsReaderPresenter(this)
        presenter.start()

        val newsId = intent.getIntExtra(NEWS_ID, -1)
        presenter.getNewsContent(newsId)
    }

    override fun initEvent() {
        //TODO("Not yet implemented")
    }

    override fun updateNewsView(news: News) {
        this.news = news
        Glide.with(this).load(this.news!!.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(app_bar_image)
    }
}