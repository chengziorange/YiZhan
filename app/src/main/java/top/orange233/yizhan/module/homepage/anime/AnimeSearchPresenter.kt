package top.orange233.yizhan.module.homepage.anime

import top.orange233.yizhan.common.repository.AnimeRepository
import top.orange233.yizhan.data.Anime

class AnimeSearchPresenter(private val view: AnimeSearchContract.View) :
    AnimeSearchContract.Presenter,
    AnimeSearchAdapter.ItemViewOnClickListener {
    private var animeList: MutableList<Anime>? = null
    private var animeSearchAdapter: AnimeSearchAdapter? = null
    var imageUrl: String? = null

    override fun getAdapter(): AnimeSearchAdapter = animeSearchAdapter!!

    override fun getSearchResult(name: String) {
        AnimeRepository.getAnimeSearchInstance().searchAnime(name)
            .subscribe({
                if (it.code == 1) {
                    return@subscribe
                }
                it.list?.forEach { item ->
                    item.cover = imageUrl
                }
                animeList?.clear()
                animeList?.addAll(it.list!!)
                view.updateList()
            }, {})
    }

    override fun start() {
        if (animeList == null) {
            animeList = mutableListOf()
        }
        if (animeSearchAdapter == null) {
            animeSearchAdapter = AnimeSearchAdapter(animeList)
        }
    }

    override fun onClickAnime(anime: Anime) {
        //TODO("Not yet implemented")
    }
}