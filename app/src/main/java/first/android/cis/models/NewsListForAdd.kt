package first.android.cis.models

import com.google.gson.annotations.SerializedName

data class NewsListForAdd(
    @SerializedName("title")
    val newsTitle: String,

    @SerializedName("description")
    val newsDescription: String,

    @SerializedName("photo")
    val newsPhoto: String?,

    @SerializedName("time-date")
    val newsTimeDate: String?

)