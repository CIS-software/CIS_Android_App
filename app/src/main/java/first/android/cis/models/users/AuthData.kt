package first.android.cis.models.users

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("Email")
    val emailUser: String,

    @SerializedName("Password")
    val passwordUser: String
)

