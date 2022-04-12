package first.android.cis.domain.usecases.signInUp

import first.android.cis.domain.repository.TokensRepository

class DeleteTokens(private val tokensRepository: TokensRepository) {
    fun execute(){
        tokensRepository.deleteTokens()
    }
}
