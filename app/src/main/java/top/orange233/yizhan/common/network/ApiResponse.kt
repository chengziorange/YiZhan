package top.orange233.yizhan.common.network

import com.google.gson.annotations.SerializedName
import top.orange233.yizhan.data.News

data class LatestNews(
    val data: String,
    val stories: List<News>,
    @SerializedName("top_stories") val topStories: List<Any>
)