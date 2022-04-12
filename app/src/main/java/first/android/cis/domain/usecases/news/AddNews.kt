package first.android.cis.domain.usecases.news

import first.android.cis.domain.models.news.NewsListForAdd
import first.android.cis.domain.models.news.NewsListItem
import first.android.cis.domain.repository.NewsRepository

class AddNews(private val newsRepository: NewsRepository) {
    suspend fun execute(newsListForAdd: NewsListForAdd, accessToken: String): NewsListItem {
        return newsRepository.addNews(newsListForAdd = newsListForAdd, accessToken = accessToken)
    }
}