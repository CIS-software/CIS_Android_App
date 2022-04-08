package first.android.cis.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import first.android.cis.network.UserRepository

class ProfileFactory(
    private val userRepository: UserRepository,
    private val accessToken: String,
    private val userId: Int
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository, accessToken, userId) as T
    }

}