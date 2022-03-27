package first.android.cis.ui.signUpIn

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import first.android.cis.databinding.FragmentSignInBinding
import first.android.cis.models.users.AuthData
import first.android.cis.models.users.UserToken
import first.android.cis.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var signInAppBTN: Button
    private lateinit var eMailEditTextSignIn: EditText
    private lateinit var passwordEditTextSignIn: EditText
    private lateinit var emailTextView: TextView
    private lateinit var passwordTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.let{
            signInAppBTN = it.signInAppBTN
            eMailEditTextSignIn = it.eMailEditTextSignIn
            passwordEditTextSignIn = it.passwordEditTextSignIn
            emailTextView = it.emailTextView
            passwordTextView = it.passwordTextView
        }
        signInAppBTN.setOnClickListener{
            val email: String = eMailEditTextSignIn.text.toString()
            val password: String = passwordEditTextSignIn.toString()
            val checkAuthData = CheckInputData()
            if (checkAuthData.checkAuthData(email, password,emailTextView,passwordTextView, activity)){
                val authData = AuthData(email, password)
                CoroutineScope(Dispatchers.Main).launch {
                    loginUser(authData, signInAppBTN)
                }
            }
        }
        return binding.root
    }

    private fun loginUser(userAuth: AuthData, signInAppBTN: Button){
        Retrofit.usersApi.signIn(userAuth).enqueue(
            object : Callback<UserToken> {
                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                }
                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                    val tokens = response.body()
                    if (tokens != null){
                        val sharedPreference =  requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
                        val editor = sharedPreference.edit()
                        editor.putString("access_token",tokens.accessToken)
                        editor.putString("refresh_token",tokens.refreshToken)
                        editor.apply()
                        signInAppBTN.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToNavigationNews())
                    }
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}