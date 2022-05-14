package com.cis.domain.usecases.user

import com.cis.domain.models.user.AuthData
import com.cis.domain.models.user.UserToken
import com.cis.domain.repository.UserRepository
import retrofit2.Response

class SignInUser(private val userRepository: UserRepository) {
    suspend fun execute(authData: AuthData): Response<UserToken>{
        return userRepository.signInUser(authData = authData)
    }
}