package first.android.cis.domain.usecases.news

import first.android.cis.domain.repository.NewsRepository

class DeleteNews(private val newsRepository: NewsRepository) {
    suspend fun execute(id: Int, accessToken: String){
        return newsRepository.deleteNews(id = id, accessToken = accessToken)
    }
}