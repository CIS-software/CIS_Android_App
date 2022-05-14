package first.android.cis.presentation.signUpIn.signIn

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.user.AuthData
import com.cis.domain.models.user.UserToken
import com.cis.domain.repository.TokensRepository
import com.cis.domain.usecases.user.SignInUser
import kotlinx.coroutines.launch
import retrofit2.Response

class SignInViewModel(private val tokensRepository: TokensRepository, private val signInUser: SignInUser): ViewModel() {
    val signInUserResponse: MutableLiveData<Response<UserToken>> = MutableLiveData()

    fun signInUser(authData: AuthData){
        viewModelScope.launch {
            try{
                signInUserResponse.value = signInUser.execute(authData = authData)
                val tokens = signInUserResponse.value
            } catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString())
            }
        }
    }

    fun saveTokens(userToken: UserToken){
        tokensRepository.saveTokens(userToken)
    }
}