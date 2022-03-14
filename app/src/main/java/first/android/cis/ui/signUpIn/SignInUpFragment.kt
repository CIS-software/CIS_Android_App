package first.android.cis.ui.signUpIn

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import first.android.cis.R


class SignInUpFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sign_in_up, container, false)
        val signInBTN: Button = root.findViewById(R.id.signInButton)
        val action = SignInUpFragmentDirections.actionRegAuthFragmentToSignInFragment()
        signInBTN.setOnClickListener{
            signInBTN.findNavController().navigate(action)
        }
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setShowHideAnimationEnabled(false)
        return root
    }

}