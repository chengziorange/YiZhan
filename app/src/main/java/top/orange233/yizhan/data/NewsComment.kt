package top.orange233.yizhan.data

import com.google.gson.annotations.SerializedName

data class NewsComment(
    @SerializedName("id")
    var commentId: Long?,

    @SerializedName("user_id")
    var userId: String?,

    var username: String?,

    @SerializedName("create_time")
    var createTime: Long?,

    var content: String?
)