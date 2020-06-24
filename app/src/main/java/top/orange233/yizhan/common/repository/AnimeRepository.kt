package top.orange233.yizhan.common.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import top.orange233.yizhan.common.network.AnimeListService

class AnimeRepository private constructor() {
    private lateinit var animeListService: AnimeListService

    private constructor(animeListService: AnimeListService) : this() {
        this.animeListService = animeListService
    }

    companion object {
        private val ANIME_LIST_INSTANCE: AnimeRepository =
            AnimeRepository(AnimeListService.create())

        fun getAnimeListInstance() = ANIME_LIST_INSTANCE
    }

    fun getLatestAnime(page: Int) =
        animeListService.getLatestAnime(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}