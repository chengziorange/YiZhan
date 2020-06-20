package top.orange233.yizhan.data

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("id") var newsId: Int?,
    var title: String?,

    var url: String?,

    @SerializedName("image")
    var imageUrl: String?,

    @SerializedName("images")
    var smallImageUrl: List<String>?,

    @SerializedName("image_source")
    var imageSource: String?,

    var type: Int?,

    var body: String?,

    var css: List<String>?
)