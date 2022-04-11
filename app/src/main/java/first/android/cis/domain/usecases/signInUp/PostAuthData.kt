package first.android.cis.domain.usecases.signInUp

import first.android.cis.domain.models.user.AuthData
import first.android.cis.domain.repository.TokensRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostAuthData(private val tokenRepository: TokensRepository) {
    fun postAuthData(authData: AuthData) {
        tokenRepository.signInService(authData)
    }
}