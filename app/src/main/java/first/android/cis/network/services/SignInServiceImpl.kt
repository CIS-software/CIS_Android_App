package first.android.cis.network.services

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import first.android.cis.data.tokens.TokensRepositoryImpl
import first.android.cis.domain.models.user.AuthData
import first.android.cis.domain.models.user.UserToken
import first.android.cis.network.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInServiceImpl(val context: Context){
     fun signInService(userAuth: AuthData, button: Button, action: NavDirections){
        val tokenRepository by lazy{ TokensRepositoryImpl(context)}
        Retrofit.usersApi.signIn(userAuth).enqueue(
            object : Callback<UserToken> {
                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                    Toast.makeText(context, "Что-то пошло не так!", Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                    val tokens = response.body()
                    if (tokens != null){
                        tokenRepository.saveTokens(tokens)
                        button.findNavController().navigate(action)
                    }
                }
            }
        )
    }
}