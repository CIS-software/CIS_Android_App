package first.android.cis.domain.models.user

import com.google.gson.annotations.SerializedName

data class UserToken (
    @SerializedName("id")
    val userId: Int,

    @SerializedName("access-token")
    val accessToken: String,

    @SerializedName("refresh-token")
    val refreshToken: String
    )

