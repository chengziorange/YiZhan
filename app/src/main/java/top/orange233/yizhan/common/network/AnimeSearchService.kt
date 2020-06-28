package top.orange233.yizhan.common.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeSearchService {

    @GET(NetConfig.Anime.METHOD_SEARCH_ANIME_BY_NAME)
    fun searchAnime(@Query("ysname") name: String): Single<SearchAnime>

    @GET(NetConfig.Anime.METHOD_GET_EPISODE_INFO)
    fun getEpisodeInfo(@Query("ysurl") url: String): Single<AnimeWithUrl>

    companion object {
        fun create(): AnimeSearchService {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetConfig.Anime.BASE_URL_ANIME_SEARCH)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetClient.client)
                .build()
            return retrofit.create()
        }
    }
}