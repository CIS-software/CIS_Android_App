package first.android.cis.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import first.android.cis.network.newsAPI.NewsList
import first.android.cis.network.newsAPI.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel : ViewModel() {
    var repo = NewsRepository()
    val myNewsList: MutableLiveData<Response<NewsList>> = MutableLiveData()

    fun getNewsVM(){
        viewModelScope.launch {
            myNewsList.value =  repo.getNewsRepo()
        }
    }
}