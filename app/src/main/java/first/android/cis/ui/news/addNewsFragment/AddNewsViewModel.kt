package first.android.cis.ui.news.addNewsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddNewsViewModel : ViewModel() {
    private val _helloworld = MutableLiveData<String>().apply {
        value = "Hello world"
    }

    val helloworld: LiveData<String> = _helloworld
}