package top.orange233.yizhan.module.homepage.anime

import android.content.Context
import top.orange233.yizhan.module.base.BasePresenter

interface AnimeContract {
    interface View {
        fun getViewContext(): Context
        fun updateAnimeList()
        fun finishRefresh()
        fun finishLoadMore()
    }

    interface Presenter : BasePresenter {
        fun getAdapter(): AnimeAdapter
        fun refreshAnimeList()
        fun loadMoreAnime()
    }
}