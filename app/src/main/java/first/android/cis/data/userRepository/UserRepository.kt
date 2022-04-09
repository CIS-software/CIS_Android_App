package first.android.cis.data.userRepository

import first.android.cis.domain.models.user.UserInfo
import first.android.cis.network.Retrofit
import retrofit2.Response

class UserRepository {
   suspend fun getUserInfoRepo(userId: Int, accessToken: String): Response<UserInfo> {
        return Retrofit.usersApi.getUserInfo(userId, accessToken)
    }
}