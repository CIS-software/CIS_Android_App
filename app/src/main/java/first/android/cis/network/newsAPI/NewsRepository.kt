package first.android.cis.network.newsAPI

import first.android.cis.network.Retrofit
import retrofit2.Response

class NewsRepository{
  suspend fun getNewsRepo(): Response<NewsList> {
       return Retrofit.newsApi.getNews()
   }
}