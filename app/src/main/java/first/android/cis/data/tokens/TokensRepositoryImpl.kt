package first.android.cis.data.tokens

import android.content.Context
import first.android.cis.domain.models.user.UserToken
import first.android.cis.domain.repository.TokensRepository


private const val SHARED_PREF_NAME = "SHARED_PREF"
private const val KEY_ACCESS_TOKEN = "access_token"
private const val KEY_REFRESH_TOKEN = "refresh_token"
private const val KEY_USER_ID = "user_id"
private const val BEARER = "Bearer "

class TokensRepositoryImpl(private val context: Context):
    TokensRepository {
    private val sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun saveTokens(tokens: UserToken){
        val editor = sharedPreference.edit()
        editor.putString(KEY_ACCESS_TOKEN,tokens.accessToken)
        editor.putString(KEY_REFRESH_TOKEN,tokens.refreshToken)
        editor.putInt(KEY_USER_ID,tokens.userId)
        editor.apply()
    }

    override fun getTokens(): UserToken {
        val accessToken = BEARER + sharedPreference.getString(KEY_ACCESS_TOKEN,"") ?: ""
        val refreshToken = BEARER + sharedPreference.getString(KEY_REFRESH_TOKEN,"") ?: ""
        val userId = sharedPreference.getInt(KEY_USER_ID,-1)
        return UserToken(userId = userId, accessToken = accessToken, refreshToken = refreshToken)
    }

    override fun deleteTokens() {
        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }
}