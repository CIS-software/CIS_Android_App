package com.example.data.network.services

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavDirections
import first.android.cis.domain.models.user.AuthData
import first.android.cis.domain.models.user.UserSignInfo
import com.example.data.network.Retrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService(val context: Context) {
    fun createUserService(userSignInfo: UserSignInfo, button: Button, action: NavDirections) {
        Retrofit.usersApi.createUserInfo(userSignInfo).enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val authData = AuthData(userSignInfo.eMail, userSignInfo.password)
                    val signInServiceImpl = SignInServiceImpl(context)
                    Toast.makeText(context, "Регистрация прошла успешно!", Toast.LENGTH_LONG).show()
                    signInServiceImpl.signInService(authData, button, action)
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(context, "Что-то пошло не так!", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}
