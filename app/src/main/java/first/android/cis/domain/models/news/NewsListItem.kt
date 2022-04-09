package first.android.cis.domain.models

import com.google.gson.annotations.SerializedName

data class NewsListItem(
    @SerializedName("id")
    val newsId: Int,

    @SerializedName("title")
    val newsTitle: String,

    @SerializedName("description")
    val newsDescription: String,

    @SerializedName("photo")
    val newsPhoto: String?,

    @SerializedName("time-date")
    val newsTimeDate: String?
)

