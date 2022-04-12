package first.android.cis.domain.repository

import first.android.cis.domain.models.user.UserToken


interface TokensRepository {
    fun saveTokens(tokens: UserToken)

    fun getTokens(): UserToken

    fun deleteTokens()
}