package com.cis.domain.models.user

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")
    val userId: Int,

    @SerializedName("name")
    val userName: String,

    @SerializedName("surname")
    val userSurname: String,

    @SerializedName("town")
    val userTown: String,

    @SerializedName("age")
    val userAge: String,

    @SerializedName("belt")
    val userBelt: String,

    @SerializedName("weight")
    val userWeight: String,

    @SerializedName("id_iko")
    val userIdIko: String
)
