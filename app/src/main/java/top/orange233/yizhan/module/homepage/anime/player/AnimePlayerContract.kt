package top.orange233.yizhan.module.homepage.anime.player

import top.orange233.yizhan.common.network.AnimeWithUrl
import top.orange233.yizhan.module.base.BasePresenter

interface AnimePlayerContract {
    interface View {
        fun loadEpisode(animeWithUrl: AnimeWithUrl)
    }

    interface Presenter : BasePresenter {
        fun fetchEpisodeInfo(animeUrl: String)
    }
}