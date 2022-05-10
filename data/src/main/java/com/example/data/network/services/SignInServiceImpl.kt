package com.example.data.network.services

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.data.tokensRepository.TokensRepositoryImpl
import com.cis.domain.models.user.AuthData
import com.cis.domain.models.user.UserToken
import com.example.data.network.Retrofit
import com.example.data.storage.sharedpref.SharedPrefTokensStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInServiceImpl(val context: Context){
     fun signInService(userAuth: AuthData, button: Button, action: NavDirections){
         val tokensStorage by lazy{ SharedPrefTokensStorage(context) }
         val tokenRepository by lazy{ TokensRepositoryImpl(tokensStorage = tokensStorage)}
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