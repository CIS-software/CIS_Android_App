package first.android.cis.domain.usecases.signInUp

import first.android.cis.domain.models.user.UserToken
import first.android.cis.domain.repository.TokensRepository

class GetTokens(private val tokensRepository: TokensRepository) {
    fun execute(): UserToken {
        return tokensRepository.getTokens()
    }
}