package first.android.cis.ui.news.openedNewsFragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
open class OpenedNewsViewModel(application: Application, val newsHead: String, val newsDiscript: String) : ViewModel() {
    // by lazy для того чтобы лайв дата не создавалась каждый раз при обращении к классу
    private val _openedHeading = MutableLiveData<String>().apply{
        value = newsHead
    }
    private val _openedDiscription = MutableLiveData<String>().apply {
        value = newsDiscript
    }
    val openedHeading: LiveData<String> = _openedHeading
    val openedDiscript: LiveData<String> =  _openedDiscription
}