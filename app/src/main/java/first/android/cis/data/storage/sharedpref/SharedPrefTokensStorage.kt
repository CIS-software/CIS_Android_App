package first.android.cis.data.storage.sharedpref

import android.content.Context
import first.android.cis.data.storage.TokensStorage
import first.android.cis.data.storage.models.Tokens

private const val SHARED_PREF_NAME = "SHARED_PREF"
private const val KEY_ACCESS_TOKEN = "access_token"
private const val KEY_REFRESH_TOKEN = "refresh_token"
private const val KEY_USER_ID = "user_id"
private const val BEARER = "Bearer "

class SharedPrefTokensStorage(context: Context) : TokensStorage {
    private val sharedPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    override fun save(tokens: Tokens) {
        val editor = sharedPreference.edit()
        editor.putString(KEY_ACCESS_TOKEN,tokens.accessToken)
        editor.putString(KEY_REFRESH_TOKEN,tokens.refreshToken)
        editor.putInt(KEY_USER_ID,tokens.userId)
        editor.apply()
    }

    override fun get(): Tokens {
        val accessToken = BEARER + sharedPreference.getString(KEY_ACCESS_TOKEN,"") ?: ""
        val refreshToken = BEARER + sharedPreference.getString(KEY_REFRESH_TOKEN,"") ?: ""
        val userId = sharedPreference.getInt(KEY_USER_ID,-1)
        return Tokens(userId = userId, accessToken = accessToken, refreshToken = refreshToken)
    }

    override fun delete() {
        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }
}