package first.android.cis.ui.news.mainNewsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import first.android.cis.network.NewsRepository

class NewsFactory(private val repository: NewsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}