package first.android.cis.models.users

import com.google.gson.annotations.SerializedName

data class UserToken (
    @SerializedName("access-token")
    val accessToken: String,

    @SerializedName("refresh-token")
    val refreshToken: String
    )

