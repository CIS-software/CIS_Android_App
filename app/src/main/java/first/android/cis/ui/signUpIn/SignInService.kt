package first.android.cis.ui.signUpIn

import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import first.android.cis.MainActivity
import first.android.cis.models.users.AuthData
import first.android.cis.models.users.UserToken
import first.android.cis.network.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInService {
    fun loginUser(
        userAuth: AuthData,
        signInAppBTN: Button,
        requireActivity: FragmentActivity,
        navigateAction: NavDirections
    ){
        Retrofit.usersApi.signIn(userAuth).enqueue(
            object : Callback<UserToken> {
                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                    Toast.makeText(requireActivity, "Что-то пошло не так!", Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                    val tokens = response.body()
                    if (tokens != null){
                        val sharedPreference =  requireActivity.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
                        val editor = sharedPreference.edit()
                        editor.putString("access_token",tokens.accessToken)
                        editor.putString("refresh_token",tokens.refreshToken)
                        editor.putInt("userId",tokens.userId)
                        editor.apply()
                        signInAppBTN.findNavController().navigate(navigateAction)
                    }
                }
            }
        )
    }
}