package first.android.cis.data.userRepository

import android.content.Context
import first.android.cis.domain.models.user.UserInfo
import first.android.cis.domain.repository.UserRepository
import first.android.cis.network.Retrofit
import retrofit2.Response

class UserRepositoryImpl(val context: Context): UserRepository {
    override suspend fun getUserInfoRepo(userId: Int, accessToken: String): Response<UserInfo> {
        return Retrofit.usersApi.getUserInfo(userId, accessToken)
    }
}