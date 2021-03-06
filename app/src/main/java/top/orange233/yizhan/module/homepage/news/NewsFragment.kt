package top.orange233.yizhan.module.homepage.news

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class NewsFragment : BaseFragment(), NewsContract.View {
    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.fragment_news

    override fun initView() {
        presenter = NewsPresenter(this)
        presenter.start()

        refresh_layout.setRefreshHeader(refresh_header)
        refresh_layout.setRefreshFooter(refresh_footer)

        rv_news.adapter = presenter.getAdapter()
        rv_news.layoutManager = LinearLayoutManager(this.context)
    }

    override fun initEvent() {
        refresh_layout.setOnRefreshListener { presenter.refreshNewsList() }
        refresh_layout.setOnLoadMoreListener { presenter.loadMoreNews() }
        refresh_layout.autoRefresh()
    }

    override fun updateNewsList() {
        presenter.getAdapter().notifyDataSetChanged()
    }

    override fun getViewContext(): Context = requireContext()

    override fun finishRefresh() {
        if (refresh_layout != null) {
            refresh_layout.finishRefresh(true)
        }
    }

    override fun finishLoadMore() {
        if (refresh_layout != null) {
            refresh_layout.finishLoadMore(true)
        }
    }
}