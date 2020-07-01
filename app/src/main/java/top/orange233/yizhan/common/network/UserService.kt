package top.orange233.yizhan.common.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*

interface UserService {

    @FormUrlEncoded
    @POST(NetConfig.Backend.METHOD_LOGIN)
    fun login(
        @Field("user_id") userId: String,
        @Field("password") password: String
    ): Single<SingleStatus>

    @FormUrlEncoded
    @POST(NetConfig.Backend.METHOD_REGISTER)
    fun register(
        @Field("email") email: String,
        @Field("password") password: String
    ): Single<Register>

    @GET(NetConfig.Backend.METHOD_GET_PROFILE)
    fun getProfile(): Single<Profile>

    @FormUrlEncoded
    @POST(NetConfig.Backend.METHOD_CHANGE_PROFILE)
    fun changeProfile(
        @Field("user_name")
        userName: String?,
        @Field("password")
        password: String?,
        @Field("avatar_base64")
        avatarBase64: String?,
        @Field("gender")
        gender: String?
    ): Single<Profile>

    @GET(NetConfig.Backend.METHOD_GET_NEWS_COMMENT)
    fun getNewsComment(@Query("news_id") newsId: String): Single<NewsCommentResponse>

    @POST(NetConfig.Backend.METHOD_ADD_NEWS_COMMENT)
    fun addNewsComment(
        @Query("news_id") newsId: String,
        @Query("content") content: String
    ): Single<SingleStatus>

    companion object {
        fun create(): UserService {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetConfig.Backend.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetClient.client)
                .build()
            return retrofit.create()
        }
    }
}