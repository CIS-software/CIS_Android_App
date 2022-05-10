package com.cis.domain.usecases.signInUp

import com.cis.domain.repository.TokensRepository

class DeleteTokens(private val tokensRepository: TokensRepository) {
    fun execute(){
        tokensRepository.deleteTokens()
    }
}
