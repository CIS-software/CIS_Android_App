package com.cis.domain.repository

import com.cis.domain.models.user.*
import okhttp3.ResponseBody
import retrofit2.Response

interface UserRepository {
    suspend fun getUserInfoRepo(idAndAccessToken: IdAndAccessToken): Response<UserInfo>
    suspend fun createUser(userSignInfo: UserSignInfo): Response<ResponseBody>
    suspend fun signInUser(authData: AuthData): Response<UserToken>
}