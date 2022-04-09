package first.android.cis.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import first.android.cis.data.userRepository.UserRepository

class ProfileFactory(
    private val userRepository: UserRepository,
    private val accessToken: String,
    private val userId: Int
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository, accessToken, userId) as T
    }

}