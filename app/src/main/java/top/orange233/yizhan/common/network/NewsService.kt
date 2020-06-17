package top.orange233.yizhan.common.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface NewsService {

    @GET(NetConfig.News.METHOD_GET_LATEST_NEWS)
    fun getLatestNews(): Single<LatestNews>

    companion object{
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