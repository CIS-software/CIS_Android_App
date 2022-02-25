package first.android.cis.network

import first.android.cis.models.NewsList
import retrofit2.Response

class NewsRepository{
  suspend fun getNewsRepo(): Response<NewsList> {
       return Retrofit.newsApi.getNews()
   }
}