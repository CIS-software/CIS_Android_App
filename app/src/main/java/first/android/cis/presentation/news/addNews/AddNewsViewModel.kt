package first.android.cis.presentation.news.addNews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.news.NewsListForAdd
import com.cis.domain.models.news.NewsListItem
import com.cis.domain.usecases.news.AddNews
import com.cis.domain.usecases.signInUp.GetTokens
import kotlinx.coroutines.launch
import retrofit2.Response

class AddNewsViewModel(private val addNews: AddNews, private val getTokens: GetTokens): ViewModel() {
    val itemForAddNews: MutableLiveData<Response<NewsListItem>> = MutableLiveData()
    fun addNews(newsListForAdd: NewsListForAdd){
        viewModelScope.launch {
            try{
                itemForAddNews.value = addNews.execute(newsListForAdd = newsListForAdd, accessToken = getTokens.execute().accessToken)
            }catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString())
            }
        }
    }
}