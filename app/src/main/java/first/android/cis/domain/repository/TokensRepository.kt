package first.android.cis.domain.repository

import first.android.cis.domain.models.user.AuthData
import first.android.cis.domain.models.user.UserToken


interface TokensRepository {
    fun signInService(userAuth: AuthData)

    fun saveTokens(tokens: UserToken)

    fun showError(): Boolean

    fun getTokens(): UserToken
}