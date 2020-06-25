package top.orange233.yizhan.module.homepage.anime

import android.content.Context
import top.orange233.yizhan.module.base.BasePresenter

interface AnimeSearchContract {
    interface View {
        fun getViewContext(): Context
        fun updateList()
    }

    interface Presenter : BasePresenter {
        fun getAdapter(): AnimeSearchAdapter
        fun getSearchResult(name: String)
    }
}