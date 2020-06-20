package top.orange233.yizhan.module.homepage.news.reader

import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_news_reader.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseActivity

class NewsReaderActivity : BaseActivity(), NewsReaderContract.View {
    companion object {
        const val NEWS_ID = "news_id"
        const val NEWS_URL = "news_url"
        const val NEWS_TITLE = "news_title"
    }

    private lateinit var presenter: NewsReaderPresenter

    override fun getLayout(): Int = R.layout.activity_news_reader

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            transparentStatusBar()
            statusBarDarkFont(true)
        }

        presenter = NewsReaderPresenter(this)
        presenter.start()

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.title = intent.getStringExtra(NEWS_TITLE)

        wv_news.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    refresh_layout.finishRefresh()
                }
            }
        }

        refresh_layout.setRefreshHeader(refresh_header)
        refresh_layout.setRefreshFooter(refresh_footer)

        rv_news_comment.adapter = presenter.getAdapter()
        rv_news_comment.layoutManager = LinearLayoutManager(this)
        rv_news_comment.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun initEvent() {
        refresh_layout.setOnRefreshListener {
            val newsUrl = intent.getStringExtra(NEWS_URL)
            wv_news.loadUrl(newsUrl)
            presenter.refreshPage()
        }
        refresh_layout.setOnLoadMoreListener { presenter.loadMoreComment() }
        refresh_layout.autoRefresh()
    }

    override fun finishRefreshPage() {
        refresh_layout.finishRefresh()
    }

    override fun finishLoadMore() {
        refresh_layout.finishLoadMore()
    }
}