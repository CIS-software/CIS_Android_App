package first.android.cis.network

import first.android.cis.models.users.AuthData
import first.android.cis.models.users.UserInfo
import first.android.cis.models.users.UserToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {
    @POST("/create-user")
    fun createUserInfo(@Body userInfo: UserInfo): Call<ResponseBody>

    @POST("/login")
    fun signIn(@Body authData: AuthData): Call<UserToken>
}