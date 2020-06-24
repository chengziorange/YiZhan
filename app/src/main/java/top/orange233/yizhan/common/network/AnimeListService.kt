package top.orange233.yizhan.common.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeListService {

    @GET(NetConfig.Anime.METHOD_GET_LATEST_ANIME)
    fun getLatestAnime(@Query("page") page: Int): Single<LatestAnime>

    companion object {
        fun create(): AnimeListService {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetConfig.Anime.BASE_URL_ANIME_LIST)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetClient.client)
                .build()
            return retrofit.create()
        }
    }
}