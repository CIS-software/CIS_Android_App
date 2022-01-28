package first.android.cis.ui.news.openedNews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class OpenedNewsViewModel : ViewModel() {
    // by lazy для того чтобы лайв дата не создавалась каждый раз при обращении к классу
    val openedHeading: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}