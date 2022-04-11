package first.android.cis.network.services

import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import first.android.cis.domain.models.user.AuthData
import first.android.cis.domain.models.user.UserSignInfo
import first.android.cis.network.Retrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService {
//    fun signUp(
//        userSignInfo: UserSignInfo,
//        requireActivity: FragmentActivity,
//        authData: AuthData,
//        endSignUpButton: Button,
//        actionNavigate: NavDirections,
//    ) {
//        Retrofit.usersApi.createUserInfo(userSignInfo).enqueue(
//            object : Callback<ResponseBody> {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    val signInService = SignInService()
//                    signInService.loginUser(authData, endSignUpButton, requireActivity,actionNavigate)
//                    Toast.makeText(requireActivity, "Регистрация прошла успешно!", Toast.LENGTH_LONG).show()
//                }
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    Toast.makeText(requireActivity, "Что-то пошло не так!", Toast.LENGTH_LONG).show()
//                }
//            }
//        )
//    }
}