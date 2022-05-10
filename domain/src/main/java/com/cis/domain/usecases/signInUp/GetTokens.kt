package com.cis.domain.usecases.signInUp

import com.cis.domain.models.user.UserToken
import com.cis.domain.repository.TokensRepository

class GetTokens(private val tokensRepository: TokensRepository) {
    fun execute(): UserToken {
        return tokensRepository.getTokens()
    }
}