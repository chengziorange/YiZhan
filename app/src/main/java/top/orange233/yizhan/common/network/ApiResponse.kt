package top.orange233.yizhan.common.network

import com.google.gson.annotations.SerializedName
import top.orange233.yizhan.data.Anime
import top.orange233.yizhan.data.AnimeEpisode
import top.orange233.yizhan.data.News
import top.orange233.yizhan.data.NewsComment

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

data class AnimeWithUrl(
    var code: Int?,
    var message: String?,
    var data: Anime,
    var list: List<AnimeEpisode>?
)

data class SingleStatus(
    var status: Int
)

data class Register(
    @SerializedName("user_id")
    var userId: String?,
    var status: Int
)

data class Profile(
    var status: Int,
    @SerializedName("user_id")
    var userId: String?,
    var email: String?,
    @SerializedName("user_name")
    var userName: String?,
    @SerializedName("avatar_url")
    var avatarUrl: String?,
    var gender: String?,
    @SerializedName("register_time")
    var registerTime: String?
)

data class NewsCommentResponse(
    var status: Int,
    var comments: List<NewsComment>
)