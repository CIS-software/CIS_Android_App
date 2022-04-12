package first.android.cis.presentation.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import first.android.cis.data.tokens.TokensRepositoryImpl
import first.android.cis.presentation.MainActivity
import first.android.cis.databinding.FragmentProfileBinding
import first.android.cis.domain.models.user.UserInfo
import first.android.cis.data.userRepository.UserRepositoryImpl
import first.android.cis.domain.models.user.IdAndAccessToken
import first.android.cis.domain.usecases.signInUp.DeleteTokens
import first.android.cis.domain.usecases.signInUp.GetTokens

class ProfileFragment : Fragment() {
    private val tokensRepository by lazy{TokensRepositoryImpl(context = requireActivity())}
    private val getTokens by lazy{GetTokens(tokensRepository = tokensRepository)}
    private lateinit var viewModel: ProfileViewModel
    private lateinit var viewModelFactory: ProfileFactory
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var userInfoList = emptyList<UserInfo>()
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
            val deleteTokens = DeleteTokens(tokensRepository = tokensRepository)
            deleteTokens.execute()
            exitButton.findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToRegAuthFragment())
            mainActivity.setStartDestination()
        }
        getUserInfo()
    }

    private fun getUserInfo(){
        val accessToken = "Bearer " + getTokens.execute().accessToken
        val userId = getTokens.execute().userId
        val idAndAccessToken = IdAndAccessToken(userId = userId, accessToken = accessToken)
        viewModelFactory = ProfileFactory(userRepository = UserRepositoryImpl(requireActivity()),
            idAndAccessToken = idAndAccessToken)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        viewModel.getUserInfo()
        viewModel.userInfoList.observe(viewLifecycleOwner){ response ->
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