package top.orange233.yizhan.module.homepage.anime

import android.content.Context
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_anime.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseFragment

class AnimeFragment : BaseFragment(), AnimeContract.View {
    private lateinit var presenter: AnimePresenter

    override fun getLayout(): Int = R.layout.fragment_anime

    override fun initView() {
        presenter = AnimePresenter(this)
        presenter.start()

        anime_refresh_layout.setRefreshHeader(anime_refresh_header)
        anime_refresh_layout.setRefreshFooter(anime_refresh_footer)

        rv_anime.adapter = presenter.getAdapter()
        rv_anime.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun initEvent() {
        anime_refresh_layout.setOnRefreshListener { presenter.refreshAnimeList() }
        anime_refresh_layout.setOnLoadMoreListener { presenter.loadMoreAnime() }
        anime_refresh_layout.autoRefresh()
    }

    override fun getViewContext(): Context = requireContext()

    override fun updateAnimeList() {
        presenter.getAdapter().notifyDataSetChanged()
    }

    override fun finishRefresh() {
        if (anime_refresh_layout != null) {
            anime_refresh_layout.finishRefresh(true)
        }
    }

    override fun finishLoadMore() {
        if (anime_refresh_layout != null) {
            anime_refresh_layout.finishLoadMore()
        }
    }
}