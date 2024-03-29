package first.android.cis.presentation.news.mainNews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.news.NewsList
import com.cis.domain.usecases.news.GetNews
import com.cis.domain.usecases.signInUp.GetTokens
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val getTokens: GetTokens,
                    private val getNews: GetNews
                    ) : ViewModel() {
    val myNewsList: MutableLiveData<Response<NewsList>> = MutableLiveData()
    fun getNewsVM(){
        viewModelScope.launch {
            try{
                myNewsList.value =  getNews.execute(accessToken = getTokens.execute().accessToken)
            }catch (exception: Exception){
                Log.d("NETWORK ERROR", exception.toString() )
            }
        }
    }
}