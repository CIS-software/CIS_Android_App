package com.example.data.network.api

import com.cis.domain.models.user.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersApi {
    @POST("/create-user")
    suspend fun createUser(@Body userSignInfo: UserSignInfo): Response<Throwable>

    @POST("/login")
    suspend fun signIn(@Body authData: AuthData): Response<UserToken>

    @POST("/user/{id}")
    suspend fun getUserInfo(@Path("id")userId: Int, @Header("Authorization") accessToken: String): Response<UserInfo>
}