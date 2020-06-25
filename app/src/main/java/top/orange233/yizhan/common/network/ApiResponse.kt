package top.orange233.yizhan.common.network

import com.google.gson.annotations.SerializedName
import top.orange233.yizhan.data.Anime
import top.orange233.yizhan.data.News

data class LatestNews(
    val date: String,
    val stories: List<News>,
    @SerializedName("top_stories") val topStories: List<Any>
)

data class BeforeNews(
    val date: String,
    val stories: List<News>
)

data class LatestAnime(
    var code: Int?,
    var data: Data?,
    var message: String?
) {
    data class Data(
        @SerializedName("has_next")
        var hasNext: Int?,
        var list: List<Anime>?,
        var num: Int?,
        var size: Int?,
        var total: Int?
    )
}

data class SearchAnime(
    var code: Int?,
    var message: String?,
    var list: List<Anime>?
)