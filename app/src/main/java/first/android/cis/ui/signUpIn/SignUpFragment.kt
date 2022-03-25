package first.android.cis.ui.signUpIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import first.android.cis.R
import first.android.cis.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var nextBtnSignUp: Button
    private lateinit var passwordEditTextSignUp: EditText
    private lateinit var emailEditTextSignUp: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.let{
            nextBtnSignUp = it.nextBtnSignUp
            passwordEditTextSignUp = it.passwordEditTextSignUp
            emailEditTextSignUp = it.emailEditTextSignUp
        }
        nextBtnSignUp.setOnClickListener{
            val email: String = emailEditTextSignUp.text.toString()
            val password: String = passwordEditTextSignUp.text.toString()
            val moveToStep2 = SignUpFragmentDirections.actionSignUpFragmentToSignUpStep2Fragment(
                email, password)
            nextBtnSignUp.findNavController().navigate(moveToStep2)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}