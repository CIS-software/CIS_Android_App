package first.android.cis.domain.usecases.news

import first.android.cis.domain.models.news.NewsIdAndAccess
import first.android.cis.domain.repository.NewsRepository

class DeleteNews(private val newsRepository: NewsRepository) {
    suspend fun execute(newsIdAndAccess: NewsIdAndAccess){
        return newsRepository.deleteNews(newsIdAndAccess = newsIdAndAccess)
    }
}