package first.android.cis.domain.repository

import android.widget.Button
import androidx.navigation.NavDirections
import first.android.cis.domain.models.user.UserToken


interface TokensRepository {
    fun saveTokens(tokens: UserToken)

    fun getTokens(): UserToken
}