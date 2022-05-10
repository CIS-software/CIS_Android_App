package com.example.data.userRepository

import android.content.Context
import first.android.cis.domain.models.user.IdAndAccessToken
import first.android.cis.domain.models.user.UserInfo
import first.android.cis.domain.repository.UserRepository
import com.example.data.network.Retrofit
import retrofit2.Response

class UserRepositoryImpl(val context: Context): UserRepository {
    override suspend fun getUserInfoRepo(idAndAccessToken: IdAndAccessToken): Response<UserInfo> {
        return Retrofit.usersApi.getUserInfo(idAndAccessToken.userId, idAndAccessToken.accessToken)
    }
}