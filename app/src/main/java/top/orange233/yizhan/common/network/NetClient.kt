package top.orange233.yizhan.common.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import top.orange233.yizhan.util.Preference
import java.util.concurrent.TimeUnit

object NetClient {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .cookieJar(object : CookieJar {
            override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                cookies.forEach {
                    when (it.name()) {
                        Preference.KEY_COOKIE_JSESSIONID -> Preference.instance.putValue(
                            Preference.KEY_COOKIE_JSESSIONID,
                            it.value()
                        )
                    }
                }
            }

            override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
                val cookies = mutableListOf<Cookie>()
                cookies.add(
                    Cookie.Builder().domain(url.host()).name(Preference.KEY_COOKIE_JSESSIONID)
                        .value(Preference.instance.getValue(Preference.KEY_COOKIE_JSESSIONID, ""))
                        .build()
                )
                return cookies
            }
        })
        .build()
}