package first.android.cis.domain.models.users

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("Email")
    val emailUser: String,

    @SerializedName("Password")
    val passwordUser: String
)

