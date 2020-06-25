package top.orange233.yizhan.module.homepage.anime

import android.content.Intent
import com.orhanobut.logger.Logger
import top.orange233.yizhan.common.repository.AnimeRepository
import top.orange233.yizhan.data.Anime

class AnimePresenter(private val view: AnimeContract.View) : AnimeContract.Presenter,
    AnimeAdapter.ItemViewOnClickListener {
    private var animeList: MutableList<Anime>? = null
    private var animeAdapter: AnimeAdapter? = null
    private var pageToLoad = 2

    override fun getAdapter(): AnimeAdapter = animeAdapter!!

    override fun refreshAnimeList() {
        AnimeRepository.getAnimeListInstance().getLatestAnime(1)
            .subscribe({
                val filteredList: MutableList<Anime> = it.data!!.list!!.toMutableList()
                it.data!!.list!!.forEach { item ->
                    if (item.title!!.contains("台灣")
                        || item.title!!.contains("港澳")
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
            animeAdapter = AnimeAdapter(animeList, this)
        }
    }

    override fun onClickAnime(anime: Anime) {
        Logger.d("clicked anime")
        val intent = Intent(view.getViewContext(), AnimeSearchActivity::class.java)
        intent.putExtra(AnimeSearchActivity.ANIME_NAME, anime.title!!.split(Regex(" "), 2)[0])
        intent.putExtra(AnimeSearchActivity.ANIME_IMAGE, anime.cover)
        view.getViewContext().startActivity(intent)
    }

    // TODO 优化
    private fun formatAnimeName(name: String): String {
        val nameWithoutBlank = name.split(Regex(" "), 1)[0]
        val pureChinese = Regex("^[\\u4e00-\\u9fa5]{0,}\$")
        val wordAndNum = Regex("^[A-Za-z0-9s!?]+\$")
        if (nameWithoutBlank.matches(pureChinese)) {
            return nameWithoutBlank.substring(
                0,
                if (nameWithoutBlank.length < 4) nameWithoutBlank.length else 4
            )
        }
        val result: String
        var index = 0
        while (nameWithoutBlank.split(wordAndNum)[index] == "") {
            index++
        }
        return nameWithoutBlank.split(wordAndNum)[index]
    }
}