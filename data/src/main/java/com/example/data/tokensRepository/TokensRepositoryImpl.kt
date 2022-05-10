package com.example.data.tokensRepository

import com.example.data.storage.TokensStorage
import com.example.data.storage.models.Tokens
import com.cis.domain.models.user.UserToken
import com.cis.domain.repository.TokensRepository

class TokensRepositoryImpl(private val tokensStorage: TokensStorage):
    TokensRepository {

    override fun saveTokens(tokensDomain: UserToken){
        val tokens = Tokens(userId = tokensDomain.userId ,
            accessToken = tokensDomain.accessToken,
            refreshToken = tokensDomain.refreshToken)
        tokensStorage.save(tokens)
    }

    override fun getTokens(): UserToken {
        val result = tokensStorage.get()
        val tokens = UserToken(userId = result.userId, accessToken = result.accessToken, refreshToken = result.refreshToken)
        return tokens
    }

    override fun deleteTokens() {
        tokensStorage.delete()
    }
}