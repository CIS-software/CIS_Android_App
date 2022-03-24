package first.android.cis.ui.news.openedNewsFragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class OpenedNewsViewModel(application: Application,
                               private val newsHead: String,
                               private val newsDiscript: String,
                               private val newsTimeDate: String) : ViewModel() {
    private val _openedHeading = MutableLiveData<String>().apply{
        value = newsHead
    }
    private val _openedDiscription = MutableLiveData<String>().apply {
        value = newsDiscript
    }
    private val _openedTimeDate = MutableLiveData<String>().apply {
        value = newsTimeDate
    }
    val openedHeading: LiveData<String> = _openedHeading
    val openedDiscript: LiveData<String> =  _openedDiscription
    val openedTimeDate: LiveData<String> = _openedTimeDate
}