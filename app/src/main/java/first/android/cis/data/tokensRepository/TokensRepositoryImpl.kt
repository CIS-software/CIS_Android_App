package first.android.cis.data.tokensRepository

import first.android.cis.data.storage.TokensStorage
import first.android.cis.data.storage.models.Tokens
import first.android.cis.domain.models.user.UserToken
import first.android.cis.domain.repository.TokensRepository

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