package com.example.data.userRepository

import android.content.Context
import com.cis.domain.models.user.*
import com.cis.domain.repository.UserRepository
import com.example.data.network.Retrofit
import okhttp3.ResponseBody
import retrofit2.Response

class UserRepositoryImpl(val context: Context): UserRepository {
    override suspend fun getUserInfoRepo(idAndAccessToken: IdAndAccessToken): Response<UserInfo> {
        return Retrofit.usersApi.getUserInfo(userId = idAndAccessToken.userId,accessToken = idAndAccessToken.accessToken)
    }

    override suspend fun createUser(userSignInfo: UserSignInfo): Response<ResponseBody> {
        return Retrofit.usersApi.createUser(userSignInfo = userSignInfo)
    }

    override suspend fun signInUser(authData: AuthData): Response<UserToken> {
        return Retrofit.usersApi.signIn(authData = authData)
    }


}