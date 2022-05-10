package first.android.cis.presentation.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.user.UserInfo
import com.example.data.userRepository.UserRepositoryImpl
import com.cis.domain.models.user.IdAndAccessToken
import com.cis.domain.usecases.user.GetUser
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class ProfileViewModel(
    private val userRepository: UserRepositoryImpl,
    private val idAndAccessToken: IdAndAccessToken
) : ViewModel() {
    val getUser by lazy{GetUser(userRepository)}
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
}

