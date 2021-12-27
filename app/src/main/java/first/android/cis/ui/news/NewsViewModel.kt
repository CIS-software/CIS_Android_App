package first.android.cis.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    private val _headingList = MutableLiveData<List<String>>().apply {
        value = arrayListOf("Заголовок 1", "Заголовок2", "Заголовок3", "Заголовок4")
    }
    val text: LiveData<List<String>> = _headingList
}