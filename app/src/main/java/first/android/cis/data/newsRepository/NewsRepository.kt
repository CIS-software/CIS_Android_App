package first.android.cis.data.newsRepository

import first.android.cis.domain.models.news.NewsList
import first.android.cis.network.Retrofit
import retrofit2.Response

class NewsRepository{
  suspend fun getNewsRepo(accessToken: String): Response<NewsList> {
       return Retrofit.newsApi.getNews(accessToken)
   }
}