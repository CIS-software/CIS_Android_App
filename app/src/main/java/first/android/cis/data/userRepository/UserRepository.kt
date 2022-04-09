package first.android.cis.network

import first.android.cis.models.users.UserInfo
import first.android.cis.models.users.UserInfoList
import retrofit2.Response

class UserRepository {
   suspend fun getUserInfoRepo(userId: Int, accessToken: String): Response<UserInfo> {
        return Retrofit.usersApi.getUserInfo(userId, accessToken)
    }
}