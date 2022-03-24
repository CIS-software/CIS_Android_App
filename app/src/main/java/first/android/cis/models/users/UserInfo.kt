package first.android.cis.models.users

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("name")
    val userName: String,

    @SerializedName("surname")
    val userSurname: String,

    @SerializedName("patronomyc")
    val userPatronomyc: String,

    @SerializedName("town")
    val userTown: String,

    @SerializedName("age")
    val userAge: Int
)