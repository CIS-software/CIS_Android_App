package first.android.cis.network

import first.android.cis.models.NewsList
import retrofit2.Response

class NewsRepository{
  suspend fun getNewsRepo(accessToken: String): Response<NewsList> {
       return Retrofit.newsApi.getNews(accessToken)
   }
}