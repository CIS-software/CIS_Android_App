package first.android.cis.domain.repository

import first.android.cis.domain.models.news.NewsList
import first.android.cis.domain.models.news.NewsListForAdd
import first.android.cis.domain.models.news.NewsListItem
import retrofit2.Response

interface NewsRepository {
    suspend fun getNewsRepo(accessToken: String): Response<NewsList>

    suspend fun addNews(newsListForAdd: NewsListForAdd, accessToken: String): NewsListItem

    suspend fun deleteNews(id: Int, accessToken: String)
}