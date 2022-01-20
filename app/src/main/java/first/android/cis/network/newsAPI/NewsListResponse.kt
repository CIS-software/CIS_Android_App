package first.android.cis.network.newsAPI

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("id")
    val newsId: Int,

    @SerializedName("title")
    val newsTitle: String,

    @SerializedName("description")
    val newsDescription: String,

    @SerializedName("photo")
    val newsPhoto: String
)

