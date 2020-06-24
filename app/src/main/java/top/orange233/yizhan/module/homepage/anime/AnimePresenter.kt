package top.orange233.yizhan.module.homepage.anime

import top.orange233.yizhan.common.repository.AnimeRepository
import top.orange233.yizhan.data.Anime

class AnimePresenter(private val view: AnimeContract.View) : AnimeContract.Presenter {
    private var animeList: MutableList<Anime>? = null
    private var animeAdapter: AnimeAdapter? = null
    private var pageToLoad = 2

    override fun getAdapter(): AnimeAdapter = animeAdapter!!

    override fun refreshAnimeList() {
        AnimeRepository.getAnimeListInstance().getLatestAnime(1)
            .subscribe({
                val filteredList: MutableList<Anime> = it.data!!.list!!.toMutableList()
                it.data!!.list!!.forEach { item ->
                    if (item.title!!.contains("（僅限台灣地區）")
                        || item.title!!.contains("（僅限港澳台地區）")
                        || item.title!!.contains("（僅限港澳台及其他地區）")
                    ) {
                        filteredList.remove(item)
                    }
                }
                animeList?.clear()
                animeList?.addAll(filteredList)
                view.updateAnimeList()
                view.finishRefresh()
            }, {})
    }

    override fun loadMoreAnime() {
        if (pageToLoad == -1) {
            view.finishLoadMore()
            return
        }
        AnimeRepository.getAnimeListInstance().getLatestAnime(pageToLoad++)
            .subscribe({
                val filteredList: MutableList<Anime> = it.data!!.list!!.toMutableList()
                it.data!!.list!!.forEach { item ->
                    if (item.title!!.contains("台灣")
                        || item.title!!.contains("港澳")
                    ) {
                        filteredList.remove(item)
                    }
                }
                animeList?.addAll(filteredList)
                if (it.data?.total!! < (pageToLoad + 1) * 20) {
                    pageToLoad = -1
                }
                view.updateAnimeList()
                view.finishLoadMore()
            }, {})
    }

    override fun start() {
        if (animeList == null) {
            animeList = mutableListOf()
        }
        if (animeAdapter == null) {
            animeAdapter = AnimeAdapter(animeList)
        }
    }
}