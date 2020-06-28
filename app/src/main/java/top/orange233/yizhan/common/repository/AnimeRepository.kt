package top.orange233.yizhan.common.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import top.orange233.yizhan.common.network.AnimeListService
import top.orange233.yizhan.common.network.AnimeSearchService

class AnimeRepository private constructor() {
    private lateinit var animeListService: AnimeListService
    private lateinit var animeSearchService: AnimeSearchService

    private constructor(animeListService: AnimeListService) : this() {
        this.animeListService = animeListService
    }

    private constructor(animeSearchService: AnimeSearchService) : this() {
        this.animeSearchService = animeSearchService
    }

    companion object {
        private val ANIME_LIST_INSTANCE: AnimeRepository =
            AnimeRepository(AnimeListService.create())
        private val ANIME_SEARCH_INSTANCE: AnimeRepository =
            AnimeRepository(AnimeSearchService.create())

        fun getAnimeListInstance() = ANIME_LIST_INSTANCE
        fun getAnimeSearchInstance() = ANIME_SEARCH_INSTANCE
    }

    fun getLatestAnime(page: Int) =
        animeListService.getLatestAnime(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun searchAnime(name: String) =
        animeSearchService.searchAnime(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getEpisodeInfo(url: String) =
        animeSearchService.getEpisodeInfo(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}