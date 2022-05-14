package first.android.cis.presentation.signUpIn.signUp.signUpStep2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.user.AuthData
import com.cis.domain.models.user.UserSignInfo
import com.cis.domain.models.user.UserToken
import com.cis.domain.repository.TokensRepository
import com.cis.domain.usecases.user.CreateUser
import com.cis.domain.usecases.user.SignInUser
import kotlinx.coroutines.launch
import retrofit2.Response

class Step2ViewModel(private val createUser: CreateUser, private val signInUser: SignInUser, private val tokensRepository: TokensRepository): ViewModel() {
    val createUserResponse: MutableLiveData<Response<Throwable>> = MutableLiveData()
    val signInUserResponse: MutableLiveData<Response<UserToken>> = MutableLiveData()
    fun createUser(userSignInfo: UserSignInfo){
        viewModelScope.launch {
            try{
                createUserResponse.value = createUser.execute(userSignInfo = userSignInfo)
            } catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString())
            }
        }
    }

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