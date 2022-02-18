package first.android.cis.ui.news.openedNewsFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsFactory(val application: Application, val newsHeading: String, val newsDiscript: String):
ViewModelProvider.AndroidViewModelFactory(application){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OpenedNewsViewModel(application, newsHeading, newsDiscript) as T
    }

}