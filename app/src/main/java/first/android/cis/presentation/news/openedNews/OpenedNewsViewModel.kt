package first.android.cis.presentation.news.openedNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cis.domain.models.news.NewsIdAndAccess

open class OpenedNewsViewModel() : ViewModel() {

    private val _openedHeading = MutableLiveData<String>().apply{
        value = "newsHead"
    }
    private val _openedDiscription = MutableLiveData<String>().apply {
        value = "newsDiscript"
    }
    private val _openedTimeDate = MutableLiveData<String>().apply {
        value = "newsTimeDate"
    }
    val openedHeading: LiveData<String> = _openedHeading
    val openedDiscript: LiveData<String> =  _openedDiscription
    val openedTimeDate: LiveData<String> = _openedTimeDate

    fun setData(discription:String, timeData: String, heading: String){
        _openedHeading.value = heading
        _openedDiscription.value = discription
        _openedTimeDate.value = timeData
    }

    fun deleteNews(newsIdAndAccess: NewsIdAndAccess){

    }
}