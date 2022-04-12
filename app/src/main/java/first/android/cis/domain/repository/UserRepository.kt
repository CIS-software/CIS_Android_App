package first.android.cis.domain.repository

import first.android.cis.domain.models.user.IdAndAccessToken
import first.android.cis.domain.models.user.UserInfo
import retrofit2.Response

interface UserRepository {
    suspend fun getUserInfoRepo(idAndAccessToken: IdAndAccessToken): Response<UserInfo>
}