package com.cis.domain.repository

import com.cis.domain.models.user.UserToken


interface TokensRepository {
    fun saveTokens(tokens: UserToken)

    fun getTokens(): UserToken

    fun deleteTokens()
}