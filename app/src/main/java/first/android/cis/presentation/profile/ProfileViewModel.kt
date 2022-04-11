package first.android.cis.presentation.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import first.android.cis.domain.models.user.UserInfo
import first.android.cis.data.userRepository.UserRepositoryImpl
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class ProfileViewModel(
    private val userRepository: UserRepositoryImpl,
    private val accessToken: String,
    private val userId: Int
) : ViewModel() {
    private val _userName = MutableLiveData<String>().apply {
        value = "Имя"
    }
    private val _userSurname = MutableLiveData<String>().apply {
        value = "Фамилия"
    }
    private val _userTown = MutableLiveData<String>().apply {
        value = "Город пользовтеля"
    }
    private val _userAge = MutableLiveData<String>().apply {
        value = "Возраст"
    }
    private val _userBelt = MutableLiveData<String>().apply {
        value = "Пояс"
    }
    private val _userWeight = MutableLiveData<String>().apply {
        value = "Вес"
    }
    private val _userIdIko = MutableLiveData<String>().apply {
        value = "Айди Айко"
    }
    val userInfoList: MutableLiveData<Response<UserInfo>> = MutableLiveData()

    fun getUserInfo(){
        viewModelScope.launch {
            try{
                userInfoList.value = userRepository.getUserInfoRepo(userId, accessToken)
            } catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString())
            }
        }
    }

    //val userName: LiveData<String> = _userName
    //val userSurname: LiveData<String> = _userSurname
    //val userTown: LiveData<String> = _userTown
    //val userAge: LiveData<String> = _userAge
    //val userBelt: LiveData<String> = _userBelt
    //val userWeight: LiveData<String> = _userWeight
    //val userIdIko: LiveData<String> = _userIdIko

}

