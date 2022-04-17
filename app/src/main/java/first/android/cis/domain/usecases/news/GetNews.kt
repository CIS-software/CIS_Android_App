package first.android.cis.domain.usecases.news

import first.android.cis.domain.models.news.NewsList
import first.android.cis.domain.repository.NewsRepository
import retrofit2.Response

class GetNews(private val newsRepository: NewsRepository) {
    suspend fun execute(accessToken: String): Response<NewsList>{
        return newsRepository.getNewsRepo(accessToken = accessToken)
    }
}