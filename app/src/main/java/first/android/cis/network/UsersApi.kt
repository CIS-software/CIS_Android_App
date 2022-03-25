package first.android.cis.network

import first.android.cis.models.users.AuthData
import first.android.cis.models.users.UserInfo
import first.android.cis.models.users.UserToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {
    @POST("/user")
    suspend fun createUserInfo(@Body userInfo: UserInfo)

    @POST("/create-user-auth")
    suspend fun createUserAuth(@Body authData: AuthData)

    @POST("/login")
    fun signIn(@Body authData: AuthData): Call<UserToken>
}