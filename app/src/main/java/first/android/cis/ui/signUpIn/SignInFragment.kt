package first.android.cis.ui.signUpIn

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import first.android.cis.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val signInAppBTN: Button = root.findViewById(R.id.signInAppBTN)
        //val action = SignInFragmentDirections.actionSignInFragmentToNavigationNews()
        signInAppBTN.setOnClickListener{
            val emailEditText: EditText = root.findViewById(R.id.eMailEditTextSignIn)
            val passwordEditText: EditText = root.findViewById(R.id.passwordEditTextSignIn)
            val authData = AuthData(emailEditText.text.toString(), passwordEditText.text.toString())
            CoroutineScope(Dispatchers.Main).launch {
                loginUser(authData, signInAppBTN)
            }
        }
        return root
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
                        //Toast.makeText(activity, "Тут из префа " + sharedPreference.getString("access_token","Ошибка! Нет токена!"), Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}