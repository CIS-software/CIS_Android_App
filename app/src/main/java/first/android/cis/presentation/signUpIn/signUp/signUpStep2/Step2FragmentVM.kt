package first.android.cis.presentation.signUpIn.signUp.signUpStep2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.user.UserSignInfo
import com.cis.domain.usecases.user.CreateUser
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class Step2ViewModel(private val createUser: CreateUser): ViewModel() {
    val createUserResponse: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    fun createUser(userSignInfo: UserSignInfo){
        viewModelScope.launch {
            try{
                createUserResponse.value = createUser.execute(userSignInfo = userSignInfo)
            } catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString())
            }
        }
    }
}