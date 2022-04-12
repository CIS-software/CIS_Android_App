package first.android.cis.data.newsRepository

import first.android.cis.domain.models.news.NewsList
import first.android.cis.domain.models.news.NewsListForAdd
import first.android.cis.domain.models.news.NewsListItem
import first.android.cis.domain.repository.NewsRepository
import first.android.cis.network.Retrofit
import retrofit2.Response

class NewsRepositoryImpl: NewsRepository{
  override suspend fun getNewsRepo(accessToken: String): Response<NewsList> {
       return Retrofit.newsApi.getNews(accessToken)
   }

    override suspend fun addNews(newsListForAdd: NewsListForAdd, accessToken: String): NewsListItem {
        return Retrofit.newsApi.addNews(newsList = newsListForAdd, accessToken)
    }

    override suspend fun deleteNews(id: Int, accessToken: String) {
        return Retrofit.newsApi.deleteNews(id = id, accessToken = accessToken)
    }
}