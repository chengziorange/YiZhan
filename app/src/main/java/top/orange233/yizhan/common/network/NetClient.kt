package top.orange233.yizhan.common.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object NetClient {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true).build()
}