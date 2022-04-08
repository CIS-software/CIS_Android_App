package first.android.cis.models.users

import com.google.gson.annotations.SerializedName

data class UserSignInfo(
    @SerializedName("Email")
    val eMail: String,

    @SerializedName("Password")
    val password: String,

    @SerializedName("Name")
    val userName: String,

    @SerializedName("Surname")
    val userSurname: String,

    @SerializedName("Town")
    val userTown: String,

    @SerializedName("Age")
    val userAge: String
)