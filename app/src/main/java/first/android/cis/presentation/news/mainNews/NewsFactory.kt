package first.android.cis.presentation.news.mainNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import first.android.cis.data.newsRepository.NewsRepository

class NewsFactory(private val repository: NewsRepository, private val accessToken: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository, accessToken) as T
    }
}