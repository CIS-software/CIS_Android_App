package com.cis.domain.repository

import com.cis.domain.models.user.IdAndAccessToken
import com.cis.domain.models.user.UserInfo
import retrofit2.Response

interface UserRepository {
    suspend fun getUserInfoRepo(idAndAccessToken: IdAndAccessToken): Response<UserInfo>
}