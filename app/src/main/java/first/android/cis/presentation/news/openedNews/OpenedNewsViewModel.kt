package first.android.cis.presentation.news.openedNews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.news.NewsIdAndAccess
import com.cis.domain.usecases.news.DeleteNews
import com.cis.domain.usecases.signInUp.GetTokens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

open class OpenedNewsViewModel(private val getTokens: GetTokens, private val deleteNews: DeleteNews) : ViewModel() {

    private val _openedHeading = MutableLiveData<String>().apply{
        value = "newsHead"
    }
    private val _openedDiscription = MutableLiveData<String>().apply {
        value = "newsDiscript"
    }
    private val _openedTimeDate = MutableLiveData<String>().apply {
        value = "newsTimeDate"
    }
    val deleteResponse: MutableLiveData<Response<Throwable>> = MutableLiveData()
    val openedHeading: LiveData<String> = _openedHeading
    val openedDiscript: LiveData<String> =  _openedDiscription
    val openedTimeDate: LiveData<String> = _openedTimeDate

    fun setData(discription:String, timeData: String, heading: String){
        _openedHeading.value = heading
        _openedDiscription.value = discription
        _openedTimeDate.value = timeData
    }

    fun deleteNews(newsId: Int){
        val newsIdAndAccess = NewsIdAndAccess(id = newsId, accessToken = getTokens.execute().accessToken)
        viewModelScope.launch {
            try{
                deleteResponse.value =  deleteNews.execute(newsIdAndAccess = newsIdAndAccess)
            } catch(exception: Exception){
                Log.d("NETWORK ERROR", exception.toString())
            }
        }

    }
}