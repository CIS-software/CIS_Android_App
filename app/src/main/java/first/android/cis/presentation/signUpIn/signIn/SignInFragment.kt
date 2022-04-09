package first.android.cis.presentation.signUpIn.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import first.android.cis.databinding.FragmentSignInBinding
import first.android.cis.domain.models.user.AuthData
import first.android.cis.network.services.SignInService
import first.android.cis.domain.signUpIn.CheckInputData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            val password: String = passwordEditTextSignIn.text.toString()
            val checkAuthData = CheckInputData()
            if (checkAuthData.checkAuthData(email, password,emailTextView,passwordTextView, activity)){
                val authData = AuthData(email, password)
                val signInService = SignInService()
                val navigateAction = SignInFragmentDirections.actionSignInFragmentToNavigationNews()
                CoroutineScope(Dispatchers.Main).launch {
                    signInService.loginUser(authData, signInAppBTN, requireActivity(), navigateAction)
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}