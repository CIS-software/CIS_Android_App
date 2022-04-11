package first.android.cis.presentation.signUpIn.signUp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import first.android.cis.databinding.FragmentSignUpBinding
import first.android.cis.domain.usecases.signInUp.CheckInputData

class SignUpFragment : Fragment() {
    private val checkAuthdata = CheckInputData()
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var nextBtnSignUp: Button
    private lateinit var passwordEditTextSignUp: EditText
    private lateinit var emailEditTextSignUp: EditText
    private lateinit var emailTextView: TextView
    private lateinit var passwordTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.let{
            nextBtnSignUp = it.nextBtnSignUp
            passwordEditTextSignUp = it.passwordEditTextSignUp
            emailEditTextSignUp = it.emailEditTextSignUp
            emailTextView = it.emailTextView
            passwordTextView = it.passwordTextView
        }
        nextBtnSignUp.setOnClickListener{
            val email: String = emailEditTextSignUp.text.toString()
            val password: String = passwordEditTextSignUp.text.toString()
            val checkAuthData = CheckInputData()
            if (checkEmail() and checkPassword()){
                nextBtnSignUp.findNavController().
                navigate(SignUpFragmentDirections.
                actionSignUpFragmentToSignUpStep2Fragment(email, password))
            }
        }
        return binding.root
    }

    private fun checkEmail(): Boolean{
        emailTextView.setTextColor(Color.BLACK)
        val email: String = emailEditTextSignUp.toString()
        if (checkAuthdata.checkEmail(email = email)){
            return true
        }else {
            emailTextView.setTextColor(Color.RED)
            return false
        }
    }

    private fun checkPassword(): Boolean{
        passwordTextView.setTextColor(Color.BLACK)
        val password: String = passwordEditTextSignUp.text.toString()
        if (checkAuthdata.checkPassword(password = password)){
            return true
        }else {
            passwordTextView.setTextColor(Color.RED)
            return false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}