package top.orange233.yizhan.common.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import top.orange233.yizhan.data.News

interface NewsService {

    @GET(NetConfig.News.METHOD_GET_LATEST_NEWS)
    fun getLatestNews(): Single<LatestNews>

    @GET(NetConfig.News.METHOD_GET_BEFORE_NEWS + "{date}")
    fun getBeforeNews(@Path("date") date: String): Single<BeforeNews>

    @GET(NetConfig.News.METHOD_GET_NEWS_BY_ID + "{newsId}")
    fun getNewsById(@Path("newsId") newsId: Int): Single<News>

    companion object {
        fun create(): NewsService {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetConfig.News.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetClient.client)
                .build()
            return retrofit.create()
        }
    }
}