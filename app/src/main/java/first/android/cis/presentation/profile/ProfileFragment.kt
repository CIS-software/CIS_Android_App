package first.android.cis.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.data.storage.sharedpref.SharedPrefTokensStorage
import com.example.data.tokensRepository.TokensRepositoryImpl
import first.android.cis.presentation.MainActivity
import first.android.cis.databinding.FragmentProfileBinding
import com.cis.domain.models.user.UserInfo
import com.cis.domain.usecases.signInUp.DeleteTokens
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private val profileViewModel by viewModel<ProfileViewModel>()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    lateinit var exitButton: Button
    lateinit var userNameView: TextView
    lateinit var userSurnameView: TextView
    lateinit var userDateOfBirthView: TextView
    lateinit var userTownView: TextView
    lateinit var userWeightView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.let{
            exitButton = it.exitButton
            userNameView = it.profileName
            userSurnameView = it.profileSurname
            userDateOfBirthView = it.userYearOfBirth
            userTownView = it.userTown
            userWeightView = it.userWeight
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitButton.setOnClickListener{
            val mainActivity: MainActivity = activity as MainActivity
            profileViewModel.userLogout()
            exitButton.findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToRegAuthFragment())
            mainActivity.setStartDestination()
        }
        getUserInfo()
    }

    private fun getUserInfo(){
        profileViewModel.getUserInfo()
        profileViewModel.userInfoList.observe(viewLifecycleOwner){ response ->
            if (response.isSuccessful){
                val userInfo = response.body()
                setUserView(userInfo)
            }else {
                Toast.makeText(activity, "Ошибка подключения", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setUserView(userInfo: UserInfo?) {
        if (userInfo != null) {
            userNameView.text = userInfo.userName
            userSurnameView.text = userInfo.userSurname
            userTownView.text = userInfo.userTown
            userDateOfBirthView.text = userInfo.userAge
        }
    }
}