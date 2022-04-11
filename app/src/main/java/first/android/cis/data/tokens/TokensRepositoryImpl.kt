package first.android.cis.data.tokens

import android.content.Context
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import first.android.cis.domain.models.user.AuthData
import first.android.cis.domain.models.user.UserToken
import first.android.cis.domain.repository.TokensRepository
import first.android.cis.network.Retrofit
import first.android.cis.presentation.signUpIn.signIn.SignInFragmentDirections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SHARED_PREF_NAME = "SHARED_PREF"
private const val KEY_ACCESS_TOKEN = "access_token"
private const val KEY_REFRESH_TOKEN = "refresh_token"
private const val KEY_USER_ID = "user_id"

class TokensRepositoryImpl(private val context: Context):
    TokensRepository {
    private val sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun signInService(userAuth: AuthData){
        Retrofit.usersApi.signIn(userAuth).enqueue(
            object : Callback<UserToken> {
                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                    Toast.makeText(context, "Что-то пошло не так!", Toast.LENGTH_LONG).show()

                }
                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                    val tokens = response.body()
                    if (tokens != null){
                        saveTokens(tokens)
                    }
                }
            }
        )

    }
    override fun saveTokens(tokens: UserToken){
        val editor = sharedPreference.edit()
        editor.putString(KEY_ACCESS_TOKEN,tokens.accessToken)
        editor.putString(KEY_REFRESH_TOKEN,tokens.refreshToken)
        editor.putInt(KEY_USER_ID,tokens.userId)
        editor.apply()
    }

    override fun getTokens(): UserToken {
        val accessToken = sharedPreference.getString(KEY_ACCESS_TOKEN,"") ?: ""
        val refreshToken = sharedPreference.getString(KEY_REFRESH_TOKEN,"") ?: ""
        val userId = sharedPreference.getInt(KEY_USER_ID,-1)
        return UserToken(userId = userId, accessToken = accessToken, refreshToken = refreshToken)
    }

    override fun showError(): Boolean {
        return true
    }

}