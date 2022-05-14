package com.cis.domain.usecases.user

import com.cis.domain.models.user.UserSignInfo
import com.cis.domain.repository.UserRepository
import okhttp3.ResponseBody
import retrofit2.Response

class CreateUser(private val userRepository: UserRepository) {
    suspend fun execute(userSignInfo: UserSignInfo): Response<Throwable>{
        return userRepository.createUser(userSignInfo = userSignInfo)
    }
}