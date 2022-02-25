package first.android.cis.network.getNews

import first.android.cis.network.Retrofit
import first.android.cis.models.NewsList
import retrofit2.Response

class NewsRepository{
  suspend fun getNewsRepo(): Response<NewsList> {
       return Retrofit.newsApi.getNews()
   }
}