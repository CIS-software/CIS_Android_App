package first.android.cis.presentation.signUpIn.signIn

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import first.android.cis.databinding.FragmentSignInBinding
import com.cis.domain.models.user.AuthData
import com.cis.domain.usecases.signInUp.CheckInputData
import com.example.data.network.services.SignInServiceImpl

private const val ERROR_MESSAGE = "Ошибка! Данные введены неверно!"

class SignInFragment : Fragment() {
    private val signInService by lazy{SignInServiceImpl(requireActivity().applicationContext)}
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var signInAppBTN: Button
    private lateinit var eMailEditTextSignIn: EditText
    private lateinit var passwordEditTextSignIn: EditText
    private lateinit var emailTextView: TextView
    private lateinit var passwordTextView: TextView
    private val checkAuthData = CheckInputData()

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
            if(checkEmail() and checkPassword()){
                val authData = AuthData(eMailEditTextSignIn.text.toString(), passwordEditTextSignIn.text.toString())
                val action = SignInFragmentDirections.actionSignInFragmentToNavigationNews()
                signInService.signInService(userAuth = authData,signInAppBTN, action)
            }else{
                Toast.makeText(context, ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun checkEmail(): Boolean {
        emailTextView.setTextColor(Color.BLACK)
        val email: String = eMailEditTextSignIn.text.toString()
        if (checkAuthData.checkEmail(email = email)){
            return true
        }else {
            emailTextView.setTextColor(Color.RED)
            return false
        }
    }

    private fun checkPassword(): Boolean {
        passwordTextView.setTextColor(Color.BLACK)
        val password: String = passwordEditTextSignIn.text.toString()
        if (checkAuthData.checkPassword(password = password)){
            return true
        }else{
            passwordTextView.setTextColor(Color.RED)
            return false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}