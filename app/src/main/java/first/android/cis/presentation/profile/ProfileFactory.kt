package first.android.cis.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.userRepository.UserRepositoryImpl
import com.cis.domain.models.user.IdAndAccessToken

class ProfileFactory(
    private val userRepository: UserRepositoryImpl,
    private val idAndAccessToken: IdAndAccessToken
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository, idAndAccessToken) as T
    }
}