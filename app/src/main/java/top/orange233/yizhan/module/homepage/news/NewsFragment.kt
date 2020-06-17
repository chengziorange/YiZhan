package top.orange233.yizhan.module.homepage.news

import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*
import top.orange233.yizhan.R
import top.orange233.yizhan.data.News
import top.orange233.yizhan.module.base.BaseFragment
import top.orange233.yizhan.common.network.NewsService

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
        refresh_layout.setOnRefreshListener { refreshLayout ->
            presenter.refreshNewsList()
            refreshLayout.finishRefresh(2000)
        }
        refresh_layout.setOnLoadMoreListener { refreshLayout ->
            presenter.getMoreNews()
            refreshLayout.finishLoadMore(2000)
        }
    }

    override fun updateNewsList() {
        presenter.getAdapter().notifyDataSetChanged()
    }
}