package top.orange233.yizhan.data

import com.google.gson.annotations.SerializedName

data class Anime(
    var badge: String?,
    var badgeType: Int?,
    var cover: String?,
    var indexShow: String?,
    @SerializedName("is_finish")
    var isFinish: Int?,
    var link: String?,
    @SerializedName("media_id")
    var mediaId: Int?,
    var order: String?,
    @SerializedName("order_type")
    var orderType: String?,
    @SerializedName("season_id")
    var seasonId: Int?,
    var title: String?,
    @SerializedName("title_icon")
    var titleIcon: String?,
    @SerializedName("name")
    var searchResultName: String?,
    @SerializedName("url")
    var searchResultUrl: String?,
    @SerializedName("genre")
    var searchResultGenre: String?,
    @SerializedName("time")
    var searchResultTime: String?
)