package first.android.cis.ui.signUpIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import first.android.cis.R

class SignInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val signInAppBTN: Button = root.findViewById(R.id.signInAppBTN)
        val action = SignInFragmentDirections.actionSignInFragmentToNavigationNews()
        signInAppBTN.setOnClickListener{
            signInAppBTN.findNavController().navigate(action)
        }
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        return root
    }
}