package first.android.cis.presentation.signUpIn.startsignfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import first.android.cis.databinding.FragmentSignInUpBinding

class SignInUpFragment : Fragment() {
    private var _binding: FragmentSignInUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInUpBinding.inflate(inflater, container, false)
        binding.let{
            signInButton = it.signInButton
            signUpButton = it.signUpButton
        }
        val toSignInFragment = SignInUpFragmentDirections.actionRegAuthFragmentToSignInFragment()
        val toSignUpFragment = SignInUpFragmentDirections.actionRegAuthFragmentToSignUpFragment()
        signInButton.setOnClickListener{
            signInButton.findNavController().navigate(toSignInFragment)
        }
        signUpButton.setOnClickListener{
            signUpButton.findNavController().navigate(toSignUpFragment)
        }
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setShowHideAnimationEnabled(false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}