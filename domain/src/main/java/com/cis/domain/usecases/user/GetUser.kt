package com.cis.domain.usecases.user

import com.cis.domain.models.user.IdAndAccessToken
import com.cis.domain.models.user.UserInfo
import com.cis.domain.repository.UserRepository
import retrofit2.Response

class GetUser(private val userRepository: UserRepository) {
    suspend fun execute(idAndAccessToken: IdAndAccessToken): Response<UserInfo> {
        return userRepository.getUserInfoRepo(idAndAccessToken)
    }
}