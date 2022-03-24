package first.android.cis.ui.signUpIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import first.android.cis.R

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_sign_up, container, false)
        val nextBtn: Button = root.findViewById(R.id.nextBtnSignUp)
        val passwordEditText: EditText = root.findViewById(R.id.passwordEditTextSignUp)
        val emailEditText: EditText = root.findViewById(R.id.emailEditText)
        nextBtn.setOnClickListener{
            val email: String = emailEditText.text.toString()
            val password: String = passwordEditText.text.toString()
            val moveToStep2 = SignUpFragmentDirections.actionSignUpFragmentToSignUpStep2Fragment(
                email, password)
            nextBtn.findNavController().navigate(moveToStep2)
        }
        return root
    }
}