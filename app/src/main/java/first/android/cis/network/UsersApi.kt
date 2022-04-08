package first.android.cis.network

import first.android.cis.models.users.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersApi {
    @POST("/create-user")
    fun createUserInfo(@Body userSignInfo: UserSignInfo): Call<ResponseBody>

    @POST("/login")
    fun signIn(@Body authData: AuthData): Call<UserToken>

    @POST("/user/{id}")
    suspend fun getUserInfo(@Path("id")userId: Int, @Header("Authorization") accessToken: String): Response<UserInfo>
}