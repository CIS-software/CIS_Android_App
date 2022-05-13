package first.android.cis.presentation.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.user.UserInfo
import com.cis.domain.models.user.IdAndAccessToken
import com.cis.domain.repository.TokensRepository
import com.cis.domain.repository.UserRepository
import com.cis.domain.usecases.signInUp.DeleteTokens
import com.cis.domain.usecases.signInUp.GetTokens
import com.cis.domain.usecases.user.GetUser
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val getTokens: GetTokens,
    private val tokensRepository: TokensRepository
) : ViewModel() {
    private val idAndAccessToken = IdAndAccessToken(getTokens.execute().userId, getTokens.execute().accessToken)
    private val getUser by lazy{GetUser(userRepository)}
    val userInfoList: MutableLiveData<Response<UserInfo>> = MutableLiveData()

    fun getUserInfo(){
        viewModelScope.launch {
            try{
                userInfoList.value = getUser.execute(idAndAccessToken)
            } catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString())
            }
        }
    }

    fun userLogout(){
        val deleteTokens = DeleteTokens(tokensRepository = tokensRepository)
        deleteTokens.execute()
    }

}

