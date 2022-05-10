package first.android.cis.presentation.news.mainNews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.news.NewsList
import com.example.data.newsRepository.NewsRepositoryImpl
import com.cis.domain.usecases.news.GetNews
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepositoryImpl,
                    private val accessToken: String
                    ) : ViewModel() {
    val myNewsList: MutableLiveData<Response<NewsList>> = MutableLiveData()
    private val getNews by lazy{GetNews(newsRepository = repository)}
    fun getNewsVM(){
        viewModelScope.launch {
            try{
                myNewsList.value =  getNews.execute(accessToken = accessToken)
            }catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString() )
            }
        }
    }
}