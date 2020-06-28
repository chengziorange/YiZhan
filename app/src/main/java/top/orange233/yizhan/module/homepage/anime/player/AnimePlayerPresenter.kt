package top.orange233.yizhan.module.homepage.anime.player

import top.orange233.yizhan.common.repository.AnimeRepository

class AnimePlayerPresenter(private val view: AnimePlayerContract.View) :
    AnimePlayerContract.Presenter {

    override fun start() {
        //TODO("Not yet implemented")
    }

    override fun fetchEpisodeInfo(animeUrl: String) {
        AnimeRepository.getAnimeSearchInstance().getEpisodeInfo(animeUrl)
            .subscribe({
                view.loadEpisode(it)
            }, {})
    }
}