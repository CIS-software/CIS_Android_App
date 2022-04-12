package first.android.cis.domain.usecases.user

import first.android.cis.domain.models.user.IdAndAccessToken
import first.android.cis.domain.models.user.UserInfo
import first.android.cis.domain.repository.UserRepository
import retrofit2.Response

class GetUser(private val userRepository: UserRepository) {
    suspend fun execute(idAndAccessToken: IdAndAccessToken): Response<UserInfo> {
        return userRepository.getUserInfoRepo(idAndAccessToken)
    }
}