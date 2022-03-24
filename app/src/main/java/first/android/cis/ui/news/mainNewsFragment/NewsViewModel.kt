package first.android.cis.ui.news.mainNewsFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import first.android.cis.models.NewsList
import first.android.cis.network.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepository, private val accessToken: String) : ViewModel() {
    val myNewsList: MutableLiveData<Response<NewsList>> = MutableLiveData()

    fun getNewsVM(){
        viewModelScope.launch {
            try{
                myNewsList.value =  repository.getNewsRepo(accessToken)
            }catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString() )
            }
        }
    }
}